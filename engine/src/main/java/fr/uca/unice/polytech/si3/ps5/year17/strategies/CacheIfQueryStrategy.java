package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

public class CacheIfQueryStrategy extends Strategy
{
    public CacheIfQueryStrategy (ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints)
    {
        super(connexions, caches, endPoints);
    }

    public void apply ()
    {
        for (EndPoint endPoint : endPoints)
        {
            for (Query query : endPoint.getQueries())
            {
                if (query.getNumberOfRequests() < 500) continue;

                Video video = query.getVideo();

                for (Connexion connexion : connexions.subList(connexion -> connexion.getIdEndPoint() == endPoint.getId()))
                {
                    Cache cache = caches.get(connexion.getIdCache());

                    if (cache.addVideo(video)) break;
                }
            }
        }
    }
}
