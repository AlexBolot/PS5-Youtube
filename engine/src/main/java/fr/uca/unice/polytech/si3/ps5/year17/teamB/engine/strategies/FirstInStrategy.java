package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

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
                if (!cache.getVideos().contains(video) && cache.addVideo(video)) {
                    videoToRemove.add(video);
                }
            }
            cpy.removeAll(videoToRemove);
        }
    }
}
