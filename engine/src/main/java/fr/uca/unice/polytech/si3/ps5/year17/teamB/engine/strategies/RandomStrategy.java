package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;

import java.util.Random;

public class RandomStrategy extends Strategy {

    private ArrayList8<Video> videos  = new ArrayList8<>();
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    public RandomStrategy(DataBundle data) {
        super(data);
        videos = data.getVideos();

    }

    @Override
    public void apply() {

        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);
        int randNum;
        for (Cache cache : this.data.getCaches()) {
            for (int i = 0; i < cpy.size(); ++i) {
                Random rand = new Random();
                randNum = rand.nextInt(cpy.size() - 1);
                if (cache.getSize() >= cpy.get(randNum).getSize() && !cache.getVideos().contains(cpy.get(randNum))) {
                    cache.addVideo(cpy.get(randNum));
                    videoToRemove.add(cpy.get(randNum));
                }
            }
            cpy.removeAll(videoToRemove);
        }

    }
}
