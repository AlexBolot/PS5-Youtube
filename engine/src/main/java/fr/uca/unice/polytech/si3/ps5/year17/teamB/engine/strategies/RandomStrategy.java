package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;

import java.util.Random;

/**
 * Put il all the cache random videos
 */
public class RandomStrategy extends Strategy {

    private ArrayList8<Video> videos  = new ArrayList8<>();
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    public RandomStrategy(DataBundle data) {
        super(data, 2);
        videos = data.getVideos();

    }

    @Override
    public void apply() {

        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);
        for (Cache cache : this.data.getCaches()) {
            for (int i = 0; i < cpy.size(); ++i) {
                Video randVid = cpy.getRandom();
                if (cache.getSize() >= randVid.getSize() && !cache.getVideos().contains(randVid)) {
                    cache.addVideo(randVid);
                    videoToRemove.add(randVid);
                }
            }
            cpy.removeAll(videoToRemove);
        }

    }
}
