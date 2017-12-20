package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;

/**
 * If there is more than 500 queries for a video, they are assigned to the cache that is
 * the nearest to the endpoint.
 * (A cache is nearer when there is less latency between it and the endpoint)
 */
public class CacheIfQueryStrategy extends Strategy {

    public CacheIfQueryStrategy(DataBundle data) {
        super(data);
    }

    public void apply() {
        for (EndPoint endPoint : data.getEndPoints()) {
            for (Query query : endPoint.getQueries()) {
                if (query.getNumberOfRequests() < 500) continue;

                Video video = query.getVideo();

                for (Connection connection : data.getConnections().subList(connexion -> connexion.getIdEndPoint() == endPoint.getId())) {
                    Cache cache = data.getCaches().get(connection.getIdCache());

                    if (cache.addVideo(video)) break;
                }
            }
        }
    }
}
