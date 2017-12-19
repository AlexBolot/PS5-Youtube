package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

/**
 * Runs through all the caches and fills them with videos
 */
@SuppressWarnings("unchecked")
public class FirstInStrategy extends Strategy {
    private ArrayList8<Video> videoToRemove;

    public FirstInStrategy(DataBundle data) {
        super(data);
        this.videoToRemove = new ArrayList8<>();
    }


    @Override
    public void apply() {
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(data.getVideos());

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
