package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * <pre>
 *  _____     _                                   _               _      __          _ _
 * |_   _|   | |                                 | |             | |    / _|        | (_)
 *   | |     | |__   __ ___   _____      __ _    | |__   __ _  __| |   | |_ ___  ___| |_ _ __   __ _
 *   | |     | '_ \ / _` \ \ / / _ \    / _` |   | '_ \ / _` |/ _` |   |  _/ _ \/ _ \ | | '_ \ / _` |
 *  _| |_    | | | | (_| |\ V /  __/   | (_| |   | |_) | (_| | (_| |   | ||  __/  __/ | | | | | (_| |
 * |_____|   |_| |_|\__,_| \_/ \___|    \__,_|   |_.__/ \__,_|\__,_|   |_| \___|\___|_|_|_| |_|\__, |
 *                                                                                              __/ |
 *                                                                                             |___/
 *        _                 _      _   _     _
 *       | |               | |    | | | |   (_)
 *   __ _| |__   ___  _   _| |_   | |_| |__  _ ___
 *  / _` | '_ \ / _ \| | | | __|  | __| '_ \| / __|
 * | (_| | |_) | (_) | |_| | |_   | |_| | | | \__ \  _ _ _
 * \___,_|_.__/ \___/ \__,_|\__|   \__|_| |_|_|___/ (_|_|_)
 * </pre>
 *
 * <hr>
 * <h2>ProbaTegy :</h2>
 * <h3>Takes the cache with the biggest number of connection and put in it all the video it can handle</h3>
 * <hr>
 */
@SuppressWarnings ("unchecked")
public class ProbaTegy extends Strategy
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
    public ProbaTegy (DataBundle data)
    {
        super(data, 1);
    }

    /**
     <hr>
     <h2>Implementation of the apply method</h2>
     <h3>Takes the cache with the biggest number of connection and put in it all the video it can handle</h3>
     <hr>
     */
    @Override
    public void apply ()
    {

        HashMap<Integer, Integer> cachesConnections = new HashMap<>();
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(data.getVideos());
        ArrayList8<Video> videoToRemove = new ArrayList8<>();
        Map<Integer, Integer> sortedCachesConnections;

        for (Cache c : data.getCaches())
        {
            int nbConnexions = 0;
            nbConnexions += data.getConnections().stream().filter(co -> co.getIdCache() == c.getId()).count();
            cachesConnections.put(c.getId(), nbConnexions);
        }

        sortedCachesConnections = cachesConnections.entrySet()
                                                   .stream()
                                                   .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                                   .collect(toMap(Map.Entry::getKey,
                                                                  Map.Entry::getValue,
                                                                  (e1, e2) -> e1,
                                                                  LinkedHashMap::new));

        data.getVideos().sort(Comparator.comparing(Video::getSize).reversed());

        for (Integer cacheId : sortedCachesConnections.keySet())
        {
            for (Video video : cpy)
            {
                if (data.getCaches().get(cacheId).getSize() >= video.getSize() && !data.getCaches()
                                                                                       .get(cacheId)
                                                                                       .getVideos()
                                                                                       .contains(video))
                {
                    data.getCaches().get(cacheId).addVideo(video);
                    videoToRemove.add(video);
                }
            }

            cpy.removeAll(videoToRemove);
        }
    }
}