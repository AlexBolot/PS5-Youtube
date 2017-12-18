package fr.uca.unice.polytech.si3.ps5.year17;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
    private HashMap<String, Object> datas;

    // Contains the size of each video
    private ArrayList<Integer> videosSizes;

    // Contains the latency (value) of serving a video request from the data center to the endpoint (index)
    private ArrayList<Integer> endPointsDatacenterLatencies;

    // Contains the number of cache servers (value) that the endpoint (index) is connected to
    private ArrayList<Integer> endPointsNumberOfConnections;

    // ID (value) of the cache server (index)
    private ArrayList<Integer> cacheServerIDs;

    // Contains the latency (value) of serving a video request from this cache server to this endpoint
    private ArrayList<Integer> endPointsLatency;


    void parse(String path) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(path));
        String[] firstLine = in.readLine().split(" ");
        int numberOfVideos = Integer.parseInt(firstLine[0]);
        int numberOfEndPoints = Integer.parseInt(firstLine[1]);
        int numberOfRequestDescriptions = Integer.parseInt(firstLine[2]);
        int numberOfCacheServers = Integer.parseInt(firstLine[3]);
        int cacheServersCapacity = Integer.parseInt(firstLine[4]);
        String[] secondLine = in.readLine().split(" ");
        //Runs through the second line to get all video sizes
        for(String strSize: secondLine){
            int size = Integer.parseInt(strSize);
        }


    }
}
