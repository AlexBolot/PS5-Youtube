package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.Video;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.*;

import static java.util.stream.Collectors.toMap;

/**
 * <pre>
  _____     _                                   _               _      __          _ _
 |_   _|   | |                                 | |             | |    / _|        | (_)
   | |     | |__   __ ___   _____      __ _    | |__   __ _  __| |   | |_ ___  ___| |_ _ __   __ _
   | |     | '_ \ / _` \ \ / / _ \    / _` |   | '_ \ / _` |/ _` |   |  _/ _ \/ _ \ | | '_ \ / _` |
  _| |_    | | | | (_| |\ V /  __/   | (_| |   | |_) | (_| | (_| |   | ||  __/  __/ | | | | | (_| |
 |_____|   |_| |_|\__,_| \_/ \___|    \__,_|   |_.__/ \__,_|\__,_|   |_| \___|\___|_|_|_| |_|\__, |
                                                                                              __/ |
                                                                                              |___/

        _                 _      _   _     _
       | |               | |    | | | |   (_)
   __ _| |__   ___  _   _| |_   | |_| |__  _ ___
  / _` | '_ \ / _ \| | | | __|  | __| '_ \| / __|
 | (_| | |_) | (_) | |_| | |_   | |_| | | | \__ \  _ _ _
  \__,_|_.__/ \___/ \__,_|\__|   \__|_| |_|_|___/ (_|_|_)


 * </pre>
 */
public class ProbaTegy extends Strategy{

    private ArrayList8<Video> videos;
    private ArrayList8<Cache> caches;
    private HashMap<Integer,Integer> cachesConnexions = new HashMap<>();
    private Map<Integer,Integer>  sortedCachesSizes;
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    public ProbaTegy(ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints, ArrayList8<Video> videos) {
        super(connexions, caches, endPoints);
        int nbConnexions =0;
        this.videos = videos;
        this.caches = caches;
        for (Cache c : caches){
            for (Connexion co : connexions) {
                if(co.getIdCache() == c.getId()) nbConnexions++;
            }
            cachesConnexions.put(c.getId(), nbConnexions);
        }
        sortedCachesSizes = cachesConnexions.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1,e2) -> e1, LinkedHashMap::new));

        videos.sort(Comparator.comparing(Video::getSize).reversed());
    }

    @Override
    public void apply() {
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);
        for (Integer cacheId : sortedCachesSizes.keySet()) {
            for (Video video : cpy) {
                if (caches.get(cacheId).getSize() >= video.getSize() && !caches.get(cacheId).getVideos().contains(video)) {
                    caches.get(cacheId).addVideo(video);
                    videoToRemove.add(video);
                }
            }
            cpy.removeAll(videoToRemove);
        }
    }

}

