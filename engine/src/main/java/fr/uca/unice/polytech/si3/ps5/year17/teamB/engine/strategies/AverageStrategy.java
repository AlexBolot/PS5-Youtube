package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Connection;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 <hr>
 <h2>AverageStrategy :</h2>
 <h3>This strategy finds the average latency of every cache,
 sort them on that value and then put the videos
 in the caches with the smallest average
 latency</h3>
 <hr>
 */
public class AverageStrategy extends Strategy
{
    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove;

    /**
     <hr>
     <h2>Default Constructor of this Strategy<br>
     <br>
     See {@link Strategy#Strategy(DataBundle, int)}
     </h2>
     <hr>

     @param data The DataBundle to apply this Strategy on.
     */
    public AverageStrategy (DataBundle data)
    {
        super(data, 3);
        videos = data.getVideos();
        videoToRemove = new ArrayList8<>();
    }

    /**
     <hr>
     <h2>Implementation of the apply method</h2>
     <h3>Finds the average latency of every cache,
     sort them on that value and then put the videos
     in the caches with the smallest average
     latency</h3>
     <hr>
     */
    @Override
    public void apply ()
    {
        HashMap<Integer, Integer> cachesLatencies = new HashMap<>();
        LinkedHashMap<Integer, Integer> sortedCachesLatencies;
        ArrayList8<Integer> listID = new ArrayList8<>();
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);

        for (Cache c : data.getCaches())
        {
            int latenciesAVG;
            int latencies = 0;
            int latenciesNb = 0;

            for (Connection co : data.getConnections().subList(co -> co.getIdCache() == c.getId()))
            {
                latencies += co.getLatency();
                latenciesNb++;
            }

            latenciesAVG = latencies / latenciesNb;
            cachesLatencies.put(c.getId(), latenciesAVG);
        }

        //Sort all the caches by their average latency
        sortedCachesLatencies = cachesLatencies.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(toMap(Map.Entry::getKey,
                                                                                                                       Map.Entry::getValue,
                                                                                                                       (e1, e2) -> e1,
                                                                                                                       LinkedHashMap::new));

        for (int i : sortedCachesLatencies.keySet())
        {
            Cache cache = data.getCaches().get(i);

            for (int j = 0; j < cpy.size(); ++j)
            {
                Video video = data.getVideos().get(j);
                if (!cache.getVideos().contains(video) && isRequestedByEndPoint(video, cache) && cache.addVideo(video))
                {
                    videoToRemove.add(video);
                }
            }

            cpy.removeAll(videoToRemove);
        }
    }
}
