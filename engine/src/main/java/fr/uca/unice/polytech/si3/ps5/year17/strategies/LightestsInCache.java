package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.Video;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.Arrays;
import java.util.Comparator;

public class LightestsInCache extends Strategy {

    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    /**
     * Constructor for a strategy
     *
     * @param connexions
     * @param caches
     * @param endPoints
     */
    public LightestsInCache(ArrayList8<Connexion> connexions,
                            ArrayList8<Cache> caches,
                            ArrayList8<EndPoint> endPoints,
                            ArrayList8<Video> videos) {
        super(connexions, caches, endPoints);
        this.videos = videos;
        this.caches = caches;
        // Sort the videos list by size (ascending order)
        videos.sort(Comparator.comparing(Video::getSize));
    }

    @Override
    public void apply() {
        ArrayList8<Video> cpy = new ArrayList8<>(videos);
        for (Cache cache : this.caches) {
            for (Video video : cpy) {
                if (cache.getSize() >= video.getSize() && !cache.getVideos().contains(video)) {
                    cache.addVideo(video);
                    videoToRemove.add(video);
                }
            }
            cpy.removeAll(videoToRemove);
        }
    }
}
