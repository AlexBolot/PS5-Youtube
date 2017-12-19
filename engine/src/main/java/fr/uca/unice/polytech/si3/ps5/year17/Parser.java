package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Parser {
    private HashMap<String, Object> datas;

    private List<Video> videos = new ArrayList<>();

    private List<EndPoint> endpoints = new ArrayList<>();

    private List<Connexion> connexions = new ArrayList<>();

    private List<Cache> caches = new ArrayList<>();

    private DataCenter dataCenter = new DataCenter();

    private int numberOfCacheServers;
    private int cacheServersCapacity;

    /**
     * Read a .in file and parse the information
     *
     * @param path to the file
     * @throws IOException
     */
    void parse(String path) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path));
        String[] firstLine = in.readLine().split(" ");
        int numberOfVideos = Integer.parseInt(firstLine[0]);
        int numberOfEndpoints = Integer.parseInt(firstLine[1]);
        int numberOfRequestDescriptions = Integer.parseInt(firstLine[2]);
        numberOfCacheServers = Integer.parseInt(firstLine[3]);
        cacheServersCapacity = Integer.parseInt(firstLine[4]);
        String[] secondLine = in.readLine().split(" ");

        // Runs through the second line to get all video sizes
        for(int i = 0; i < numberOfVideos; i++){
            String strSize = secondLine[i];
            videos.add(new Video(i, Integer.parseInt(strSize)));
        }
        // Runs through the endpoints information
        for(int endpointId = 0; endpointId < numberOfEndpoints; endpointId++){
            String[] endpointsInformation = in.readLine().split(" ");
            int dataCenterLatency = Integer.parseInt(endpointsInformation[0]);
            int endpointNumberOfConnections = Integer.parseInt(endpointsInformation[1]);
            endpoints.add(new EndPoint(endpointId, new ArrayList<>(), dataCenterLatency, endpointNumberOfConnections));
            // Runs through cache server information
            for (int j = 0; j < endpointNumberOfConnections; j++) {
                String[] cacheInformation = in.readLine().split(" ");
                int cacheServerId = Integer.parseInt(cacheInformation[0]);
                int latencyCacheEndpoint = Integer.parseInt(cacheInformation[1]);
                connexions.add(new Connexion(cacheServerId, endpointId, latencyCacheEndpoint));
                caches.add(new Cache(cacheServerId, cacheServersCapacity));
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
    }

    public List<Cache> getCaches() {
        return caches;
    }

    public List<Connexion> getConnexions() {
        return connexions;
    }

    public List<EndPoint> getEndpoints() {
        return endpoints;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }
}
