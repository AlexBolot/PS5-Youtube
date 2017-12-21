package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BestForEachCacheStrategy extends Strategy
{
    /**
     Constructor for a strategy
     */
    public BestForEachCacheStrategy (DataBundle data)
    {
        super(data, 7);
    }

    @Override
    public void apply ()
    {
        data.getCaches().subList(this::hasConnexions).forEach(cache -> mostWantedVideoForCache(cache).forEach(cache::addVideo));
    }

    private boolean hasConnexions (Cache cache)
    {
        return data.getConnections().stream().anyMatch(connection -> connection.getIdCache() == cache.getId());
    }

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
