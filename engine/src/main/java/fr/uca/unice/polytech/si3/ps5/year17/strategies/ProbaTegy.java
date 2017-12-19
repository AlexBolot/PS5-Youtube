package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * <pre>
 * _____     _                                   _               _      __          _ _
 * |_   _|   | |                                 | |             | |    / _|        | (_)
 * | |     | |__   __ ___   _____      __ _    | |__   __ _  __| |   | |_ ___  ___| |_ _ __   __ _
 * | |     | '_ \ / _` \ \ / / _ \    / _` |   | '_ \ / _` |/ _` |   |  _/ _ \/ _ \ | | '_ \ / _` |
 * _| |_    | | | | (_| |\ V /  __/   | (_| |   | |_) | (_| | (_| |   | ||  __/  __/ | | | | | (_| |
 * |_____|   |_| |_|\__,_| \_/ \___|    \__,_|   |_.__/ \__,_|\__,_|   |_| \___|\___|_|_|_| |_|\__, |
 * __/ |
 * |___/
 *
 * _                 _      _   _     _
 * | |               | |    | | | |   (_)
 * __ _| |__   ___  _   _| |_   | |_| |__  _ ___
 * / _` | '_ \ / _ \| | | | __|  | __| '_ \| / __|
 * | (_| | |_) | (_) | |_| | |_   | |_| | | | \__ \  _ _ _
 * \__,_|_.__/ \___/ \__,_|\__|   \__|_| |_|_|___/ (_|_|_)
 *
 *
 * </pre>
 */
@SuppressWarnings("unchecked")
public class ProbaTegy extends Strategy {

    private Map<Integer, Integer> sortedCachesSizes;

    public ProbaTegy(DataBundle data) {
        super(data);

        int nbConnexions = 0;

        HashMap<Integer, Integer> cachesConnexions = new HashMap<>();

        for (Cache c : data.getCaches()) {
            nbConnexions += data.getConnections().stream().filter(co -> co.getIdCache() == c.getId()).count();

            cachesConnexions.put(c.getId(), nbConnexions);
        }

        sortedCachesSizes = cachesConnexions.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        data.getVideos().sort(Comparator.comparing(Video::getSize).reversed());
    }

    @Override
    public void apply() {
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(data.getVideos());

        ArrayList8<Video> videoToRemove = new ArrayList8<>();

        for (Integer cacheId : sortedCachesSizes.keySet()) {
            for (Video video : cpy) {
                if (data.getCaches().get(cacheId).getSize() >= video.getSize() && !data.getCaches().get(cacheId).getVideos().contains(video)) {
                    data.getCaches().get(cacheId).addVideo(video);
                    videoToRemove.add(video);
                }
            }

            cpy.removeAll(videoToRemove);
        }
    }
}