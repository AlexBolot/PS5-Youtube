package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;

/**
 If there is more than 500 queries for a video, they are assigned to the cache that is
 the nearest to the endpoint.
 (A cache is nearer when there is less latency between it and the endpoint)

 <hr>
 <h2>This is the Average Strategy :</h2>
 <h3>If there is more than 500 queries for a video, they are assigned to the cache that is
 the nearest to the endpoint.</h3>
 <hr>
 */
public class CacheIfQueryStrategy extends Strategy
{

    /**
     <hr>
     <h2>Default Constructor of this Strategy<br>
     <br>
     See {@link Strategy#Strategy(DataBundle, int)}
     </h2>
     <hr>

     @param data The DataBundle to apply this Strategy on.
     */
    public CacheIfQueryStrategy (DataBundle data)
    {
        super(data, 6);
    }

    public void apply ()
    {
        for (EndPoint endPoint : data.getEndPoints())
        {
            for (Query query : endPoint.getQueries())
            {
                if (query.getNumberOfRequests() < getAverageRequestAmount()) continue;

                Video video = query.getVideo();

                for (Connection connection : data.getConnections().subList(connexion -> connexion.getIdEndPoint() == endPoint.getId()))
                {
                    Cache cache = data.getCaches().get(connection.getIdCache());

                    if (cache.addVideo(video)) break;
                }
            }
        }
    }

    public int getAverageRequestAmount ()
    {
        int globalSum = 0;

        if (data.getEndPoints().isEmpty()) return 0;

        for (EndPoint endPoint : data.getEndPoints())
        {
            int localSum = endPoint.getQueries().stream().mapToInt(Query::getNumberOfRequests).sum();

            globalSum += localSum / endPoint.getQueries().size();
        }

        return globalSum / data.getEndPoints().size();
    }
}
