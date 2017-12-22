package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.io.*;

public class Parser {

    private ArrayList8<Video> videos;

    private ArrayList8<EndPoint> endpoints;

    private ArrayList8<Connection> connections;

    private ArrayList8<Cache> caches;

    private DataCenter dataCenter;

    private int numberOfVideos;
    private int numberOfEndpoints;
    private int numberOfRequestDescriptions;
    private int numberOfCacheServers;
    private int cacheServersCapacity;

    public Parser() {
        dataCenter = new DataCenter();
        caches = new ArrayList8<>();
        connections = new ArrayList8<>();
        endpoints = new ArrayList8<>();
        videos = new ArrayList8<>();
    }

    public Parser(String path) {
        this();
        parse(path);
    }

    /**
     * Reads a .in file and parse the information
     * Saves the values in a data bundle
     * @param inputStream
     * @throws IllegalArgumentException if the extension or if the data of the file is wrong
     */
    private void parse(InputStreamReader inputStream) throws IllegalArgumentException {

        try (BufferedReader in = new BufferedReader(inputStream)) {
            String[] firstLine = in.readLine().split(" ");
            if(firstLine.length != 5
                    || !firstLine[0].matches("[0-9]")
                    || !firstLine[1].matches("[0-9]")
                    || !firstLine[2].matches("[0-9]")
                    || !firstLine[3].matches("[0-9]")
                    || !firstLine[4].matches("[0-9]")) throw new IllegalArgumentException("First line must contain 5 numbers");
            numberOfVideos = Integer.parseInt(firstLine[0]);
            numberOfEndpoints = Integer.parseInt(firstLine[1]);
            numberOfRequestDescriptions = Integer.parseInt(firstLine[2]);
            numberOfCacheServers = Integer.parseInt(firstLine[3]);
            cacheServersCapacity = Integer.parseInt(firstLine[4]);
            String[] secondLine = in.readLine().split(" ");

            // Runs through the second line to get all video sizes
            for (int i = 0; i < numberOfVideos; i++) {
                if(!secondLine[i].matches("[0-9]")) throw new IllegalArgumentException("Second line must only contain numbers");
                String strSize = secondLine[i];
                videos.add(new Video(i, Integer.parseInt(strSize)));
            }
            // Runs through the endpoints information
            for (int endpointId = 0; endpointId < numberOfEndpoints; endpointId++) {
                String[] endpointsInformation = in.readLine().split(" ");
                if (endpointsInformation.length != 2 || !endpointsInformation[endpointId].matches("[0-9]")) throw new IllegalArgumentException("File must only contain numbers");
                int dataCenterLatency = Integer.parseInt(endpointsInformation[0]);
                int endpointNumberOfConnections = Integer.parseInt(endpointsInformation[1]);
                endpoints.add(new EndPoint(endpointId, new ArrayList8<>(), dataCenterLatency, endpointNumberOfConnections));
                // Runs through cache server information
                for (int j = 0; j < endpointNumberOfConnections; j++) {
                    String[] cacheInformation = in.readLine().split(" ");
                    if(cacheInformation.length != 2 || !cacheInformation[j].matches("[0-9]")) throw new IllegalArgumentException("File must only contain numbers");
                    int cacheServerId = Integer.parseInt(cacheInformation[0]);
                    int latencyCacheEndpoint = Integer.parseInt(cacheInformation[1]);
                    connections.add(new Connection(cacheServerId, endpointId, latencyCacheEndpoint));
                    Cache newCache = new Cache(cacheServerId, cacheServersCapacity);
                    if (!caches.contains(newCache)) caches.add(newCache);
                }
            }
            // Runs through the requests information
            for (int i = 0; i < numberOfRequestDescriptions; i++) {
                String[] requestInformation = in.readLine().split(" ");
                if(requestInformation.length != 3 || !requestInformation[i].matches("[0-9]")) throw new IllegalArgumentException("Requests description must contain 3 numbers");
                int idVideo = Integer.parseInt(requestInformation[0]);
                int endpointId = Integer.parseInt(requestInformation[1]);
                int numberOfRequests = Integer.parseInt(requestInformation[2]);
                EndPoint endPoint = endpoints.stream().filter(ep -> ep.getId() == endpointId).findFirst().get();
                endPoint.addQuery(new Query(numberOfRequests, videos.stream().filter(video -> video.getId() == idVideo).findFirst().get()));
            }

            dataCenter.setVideos(videos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(String path) {
        if (!path.matches("^.*\\.(in|in\\.txt)$"))
            throw new IllegalArgumentException("Only .in or .in.txt files can be read.");

        try {
            parse(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void parse(InputStream inputStream) {
        parse(new InputStreamReader(inputStream));
    }

    public ArrayList8<Cache> getCaches() {
        return caches;
    }

    public ArrayList8<Connection> getConnections() {
        return connections;
    }

    public ArrayList8<EndPoint> getEndpoints() {
        return endpoints;
    }

    public ArrayList8<Video> getVideos() {
        return videos;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }

    public DataBundle getData() {
        return new DataBundle(new ArrayList8<>(this.connections), new ArrayList8<>(this.caches),
                new ArrayList8<>(this.videos), new ArrayList8<>(this.endpoints), new DataCenter(this.dataCenter));
    }

    public int getNumberOfVideos() {
        return numberOfVideos;
    }

    public int getNumberOfEndpoints() {
        return numberOfEndpoints;
    }

    public int getNumberOfRequestDescriptions() {
        return numberOfRequestDescriptions;
    }

    public int getNumberOfCacheServers() {
        return numberOfCacheServers;
    }
}
