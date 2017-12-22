package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;

/**
 <hr>
 <h2>Cache if Query Strategy :</h2>
 <h3>
 1 - Find the average amount of request per video per endpoint<br>
 2 - Put the videos in the caches if they are requested more than the average by a endpoint.
 </h3>
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

    /**
     <hr>
     <h2>Implementation of the apply method</h2>
     <h3>
     1 - Find the average amount of request per video per endpoint<br>
     2 - Put the videos in the caches if they are requested more than the average by a endpoint.
     </h3>
     <hr>
     */
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

    /**
     <hr>
     <h2>Finds the average amount of request per video per endpoint</h2>
     <hr>

     @return The average amount of request per video per endpoint
     */
    private int getAverageRequestAmount ()
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
