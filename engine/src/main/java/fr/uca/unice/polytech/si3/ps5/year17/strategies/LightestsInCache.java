package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.Comparator;

/**
 * Put the lightests videos in cache servers only if those videos are requested by the linked endpoints
 */
public class LightestsInCache extends Strategy {

    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove;

    /**
     * Constructor for a strategy
     */
    public LightestsInCache(DataBundle data) {
        super(data);
        // Sort the videos list by size (ascending order)
        videos.sort(Comparator.comparing(Video::getSize));
        this.videoToRemove = new ArrayList8<>();
    }

    @Override
    public void apply() {
        ArrayList8<Video> cpy = new ArrayList8<>(videos);
        for (Cache cache : this.data.getCaches()) {
            for (Video video : cpy) {
                if (!cache.getVideos().contains(video)
                        && isRequestedByEndPoint(video, cache)
                        && cache.addVideo(video)) {
                    videoToRemove.add(video);
                }
            }
            cpy.removeAll(videoToRemove);
        }
    }

    /**
     * Returns true if the specified video is needed by the endpoints linked to the specified cache
     *
     * @param video
     * @param cache
     * @return true if the video is indeed needed by the endpoint
     */
    private boolean isRequestedByEndPoint(Video video, Cache cache) {
        ArrayList8<EndPoint> linkedEndpoints = new ArrayList8<>();
        for (EndPoint endpoint : data.getEndPoints()) {
            for (Connection connection : data.getConnections()) {
                if (connection.getIdCache() == cache.getId() && connection.getIdEndPoint() == endpoint.getId()) {
                    linkedEndpoints.add(endpoint);
                }
            }
        }
        for (EndPoint endpoint : linkedEndpoints) {
            for (Query query : endpoint.getQueries()) {
                if (video.getId() == query.getVideo().getId()) return true;
            }
        }
        return false;
    }
}
