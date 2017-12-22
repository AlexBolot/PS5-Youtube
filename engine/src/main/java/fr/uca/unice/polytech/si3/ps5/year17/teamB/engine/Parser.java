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
     */
    private void parse(InputStreamReader inputStream) {

        try (BufferedReader in = new BufferedReader(inputStream)) {
            String[] firstLine = in.readLine().split(" ");
            numberOfVideos = Integer.parseInt(firstLine[0]);
            numberOfEndpoints = Integer.parseInt(firstLine[1]);
            numberOfRequestDescriptions = Integer.parseInt(firstLine[2]);
            numberOfCacheServers = Integer.parseInt(firstLine[3]);
            cacheServersCapacity = Integer.parseInt(firstLine[4]);
            String[] secondLine = in.readLine().split(" ");

            // Runs through the second line to get all video sizes
            for (int i = 0; i < numberOfVideos; i++) {
                String strSize = secondLine[i];
                videos.add(new Video(i, Integer.parseInt(strSize)));
            }
            // Runs through the endpoints information
            for (int endpointId = 0; endpointId < numberOfEndpoints; endpointId++) {
                String[] endpointsInformation = in.readLine().split(" ");
                int dataCenterLatency = Integer.parseInt(endpointsInformation[0]);
                int endpointNumberOfConnections = Integer.parseInt(endpointsInformation[1]);
                endpoints.add(new EndPoint(endpointId, new ArrayList8<>(), dataCenterLatency, endpointNumberOfConnections));
                // Runs through cache server information
                for (int j = 0; j < endpointNumberOfConnections; j++) {
                    String[] cacheInformation = in.readLine().split(" ");
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
