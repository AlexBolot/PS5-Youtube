package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

@SuppressWarnings("unchecked")
public abstract class Strategy {

    protected DataBundle data;
    protected int stratID;

    /**
     * Constructor for a strategy
     */
    public Strategy(DataBundle data, int stratID) {
        this.data = data;
        this.stratID = stratID;
    }

    /**
     * Apply the strategy
     */
    public abstract void apply();

    /**
     * ToString method
     *
     * @return A representation of the Object in a String form
     */
    @Override
    public String toString() {
        long usedCache = data.getCaches().stream().filter(cache -> !cache.getVideos().isEmpty()).count();
        StringBuilder builder = new StringBuilder();
        builder.append(usedCache).append('\n');
        for (Cache cache : data.getCaches()) {
            if (cache.getVideos().isEmpty()) continue;
            builder.append(cache.getId());
            for (Video video : cache.getVideos()) builder.append(' ').append(video.getId());
            builder.append('\n');
        }
        return builder.toString();
    }

    /**
     * Returns true if the specified video is needed by the endpoints linked to the specified cache
     *
     * @param video
     * @param cache
     * @return true if the video is indeed needed by the endpoint
     */
    protected boolean isRequestedByEndPoint(Video video, Cache cache) {
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

    /**
     * Getter for the Strategy Id
     *
     * @return The Strategy Id
     */
    public int getStratID() {
        return stratID;
    }

    /**
     * Getter for the DataBundle
     *
     * @return The DataBundle of the Strategy
     */
    public DataBundle getData() {
        return data;
    }

}
