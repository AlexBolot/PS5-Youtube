package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.Comparator;

/**
 * Put the lightests videos in cache servers only if those videos are requested by the linked endpoints
 */
public class LightestsInCache extends Strategy {

    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove;

    /**
     * Constructor for a strategy
     */
    public LightestsInCache(DataBundle data) {
        super(data);
        // Sort the videos list by size (ascending order)
        videos.sort(Comparator.comparing(Video::getSize));
        this.videoToRemove = new ArrayList8<>();
    }

    @Override
    public void apply() {
        ArrayList8<Video> cpy = new ArrayList8<>(videos);
        for (Cache cache : this.data.getCaches()) {
            for (Video video : cpy) {
                if (!cache.getVideos().contains(video)
                        && isRequestedByEndPoint(video, cache)
                        && cache.addVideo(video)) {
                    videoToRemove.add(video);
                }
            }
            cpy.removeAll(videoToRemove);
        }
    }


}
