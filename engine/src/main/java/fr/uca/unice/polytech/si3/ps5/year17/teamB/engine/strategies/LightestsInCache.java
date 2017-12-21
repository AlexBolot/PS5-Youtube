package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;

import java.util.Comparator;

/**
 * Put the lightests videos in cache servers only if those videos are requested by the linked endpoints
 */
public class LightestsInCache extends Strategy {

    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove;

    /**
     * Constructor for the strategy
     * @param data
     */
    public LightestsInCache(DataBundle data) {
        super(data);
        videos = data.getVideos();
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
