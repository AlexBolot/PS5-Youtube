package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;

import java.util.ArrayList;
import java.util.HashMap;

public class DynamicStrategy extends Strategy {

    private HashMap<Cache, Video> videosToCache = new HashMap();

    public DynamicStrategy(DataBundle data) {
        super(data);
    }

    @Override
    public void apply() {

    }

    public double estimateScore(Video video, ArrayList<EndPoint> endpoints, ArrayList<Connection> connections){
        double score;
        double totalNumberOfRequests = 0.0;
        double sumOfAllTimeSavedForOneRequest = 0.0;
        double numberOfRequestsForOneEndpoint;
        double datacenterLatency;
        double cacheLatency;
        for(EndPoint endpoint: endpoints){
            numberOfRequestsForOneEndpoint = endpoint.getQueries().size();
            datacenterLatency = endpoint.getDataCenterLatency();
            cacheLatency = 0.0;
            for(Connection connection: connections){
                if(connection.getIdEndPoint() == endpoint.getId()){
                    cacheLatency = connection.getLatency();
                    break;
                }
            }
            sumOfAllTimeSavedForOneRequest += numberOfRequestsForOneEndpoint * (datacenterLatency - cacheLatency);
            totalNumberOfRequests += numberOfRequestsForOneEndpoint;
        }
        sumOfAllTimeSavedForOneRequest *= 1000.0;
        score = sumOfAllTimeSavedForOneRequest / totalNumberOfRequests;

        return score;
    }

    /*public double estimateScore(DataBundle data){
        double score;
        double totalNumberOfRequests = 0.0;
        double sumOfAllTimeSavedForOneRequest = 0.0;
        double numberOfRequestsForOneEndpoint;
        double datacenterLatency;
        double cacheLatency;
        for(EndPoint endpoint: data.getEndPoints()){
            numberOfRequestsForOneEndpoint = endpoint.getQueries().size();
            datacenterLatency = endpoint.getDataCenterLatency();
            cacheLatency = 0.0;
            for(Connection connection: data.getConnections()){
                if(connection.getIdEndPoint() == endpoint.getId()){
                    cacheLatency = connection.getLatency();
                    break;
                }
            }
            sumOfAllTimeSavedForOneRequest += numberOfRequestsForOneEndpoint * (datacenterLatency - cacheLatency);
            totalNumberOfRequests += numberOfRequestsForOneEndpoint;
        }
        sumOfAllTimeSavedForOneRequest *= 1000.0;
        score = sumOfAllTimeSavedForOneRequest / totalNumberOfRequests;

        return score;
    }*/
}
