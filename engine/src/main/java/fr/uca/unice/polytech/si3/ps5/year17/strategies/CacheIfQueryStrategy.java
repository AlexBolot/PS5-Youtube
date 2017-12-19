package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

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

                for (Connexion connexion : data.getConnexions().subList(connexion -> connexion.getIdEndPoint() == endPoint.getId())) {
                    Cache cache = data.getCaches().get(connexion.getIdCache());

                    if (cache.addVideo(video)) break;
                }
            }
        }
    }
}
