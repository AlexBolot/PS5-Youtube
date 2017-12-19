package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Put the lightests videos in cache servers
 */
public class LightestsInCache extends Strategy {

    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    /**
     * Constructor for a strategy
     */
    public LightestsInCache(DataBundle data) {
        super(data);
        // Sort the videos list by size (ascending order)
        videos.sort(Comparator.comparing(Video::getSize));
    }

    @Override
    public void apply() {
        ArrayList8<Video> cpy = new ArrayList8<>(videos);
        for (Cache cache : this.data.getCaches()) {
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
