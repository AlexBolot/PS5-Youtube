package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 <hr>
 <h2>This is the Average Strategy :</h2>
 <h3>For each Cache, finds the most wanted video by its EndPoints</h3>
 <hr>
 */
public class BestForEachCacheStrategy extends Strategy
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
    public BestForEachCacheStrategy (DataBundle data)
    {
        super(data, 7);
    }

    /**
     <hr>
     <h2>Implementation of the apply method</h2>
     <h3>For each Cache, finds the most wanted video by its EndPoints</h3>
     <hr>
     */
    @Override
    public void apply ()
    {
        data.getCaches().subList(this::hasConnexions).forEach(cache -> mostWantedVideoForCache(cache).forEach(cache::addVideo));
    }

    /**
     <hr>
     <h2>Tells weather the [cache] param is connected to at least one EndPoint</h2>
     <hr>

     @param cache Cache to be looked for in the connections list
     @return True if the Cache is connected to at least one EndPoint.<br>False otherwise
     */
    private boolean hasConnexions (Cache cache)
    {
        return data.getConnections().stream().anyMatch(connection -> connection.getIdCache() == cache.getId());
    }

    /**
     <hr>
     <h2>Returns the list of videos wanted by the EndPoints connected to the [cache] param,
     sorted by the number of request</h2>
     <hr>

     @param cache to be looked to find the EndPoints and the Videos
     @return The list of videos wanted by the EndPoints connected to the [cache] param,
     sorted by the number of request
     */
    private ArrayList<Video> mostWantedVideoForCache (Cache cache)
    {
        HashMap<Video, Integer> wantedVideos = new HashMap<>();

        ArrayList8<Connection> connectionsSubList = data.getConnections().subList(connection -> connection.getIdCache() == cache.getId());
        ArrayList8<EndPoint> endPointsSublist = data.getEndPoints().subList(endPoint -> connectionsSubList.contains(connection -> connection
                .getIdEndPoint() == endPoint.getId()));

        int cacheSize = cache.getSize();

        for (EndPoint endPoint : endPointsSublist)
        {
            for (Query query : endPoint.getQueries())
            {
                Video video = query.getVideo();
                if (cache.getVideos().contains(video)) continue;

                if (wantedVideos.containsKey(video))
                {
                    int oldAmount = wantedVideos.get(video);
                    wantedVideos.replace(video, oldAmount + 1);
                }
                else if (cacheSize >= video.getSize())
                {
                    wantedVideos.put(video, 1);
                    cacheSize -= video.getSize();
                }
            }
        }

        return wantedVideos.entrySet()
                           .stream()
                           .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))
                           .map(Map.Entry::getKey)
                           .collect(Collectors.toCollection(ArrayList8::new));
    }
}
