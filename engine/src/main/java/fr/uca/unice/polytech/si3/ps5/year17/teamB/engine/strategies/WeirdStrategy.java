package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.ArrayList;
import java.util.HashMap;

public class WeirdStrategy extends Strategy {

    private ArrayList<Video> listVideosInCache = new ArrayList<>();
    private HashMap<Cache, ArrayList<Video>> videosToCache = new HashMap();

    public WeirdStrategy(DataBundle data) {
        super(data, 8);
    }

    @Override
    public void apply() {

        // Put in the hash map an association of cashes and videos
        for (Video video : data.getVideos()) {
            connectVideoToBestCache(video, data.getEndPoints(), data.getConnections(), data.getCaches());
        }

        for (int i = 0; i < data.getCaches().size() - 1; i++) {
            for (int j = 0; j < listVideosInCache.size() - 1; j++) {
                data.getCaches().get(i).addVideo(videosToCache.get(data.getCaches().get(i)).get(j));
            }
        }
    }

    /**
     * For a given video, associates it with a cache in videosToCache HashMap.
     * The video is added to a list.
     *
     * @param video
     * @param endpoints
     * @param connections
     * @param caches
     */
    private void connectVideoToBestCache(Video video, ArrayList<EndPoint> endpoints, ArrayList<Connection> connections, ArrayList<Cache> caches) {
        for (EndPoint endpoint : endpoints) {
            for (Query query : endpoint.getQueries()) {
                if (query.getVideo().equals(video)) {
                    for (Cache cache : caches) {
                        for (Connection connection : connections) {
                            if (cache.getId() == connection.getIdCache() && endpoint.getId() == connection.getIdEndPoint()) {
                                listVideosInCache.add(video);
                                videosToCache.put(cache, listVideosInCache);
                            }
                        }
                    }
                }
            }
        }
    }

    public double estimateScore(DataBundle data) {
        double score;
        double totalNumberOfRequests = 0.0;
        double sumOfAllTimeSavedForOneRequest = 0.0;
        double numberOfRequestsForOneEndpoint;
        double datacenterLatency;
        double cacheLatency;
        for (EndPoint endpoint : data.getEndPoints()) {
            numberOfRequestsForOneEndpoint = endpoint.getQueries().size();
            datacenterLatency = endpoint.getDataCenterLatency();
            cacheLatency = 0.0;
            for (Connection connection : data.getConnections()) {
                if (connection.getIdEndPoint() == endpoint.getId()) {
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
}
