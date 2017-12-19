package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.Random;

public class RandomStrategy extends Strategy {

    private ArrayList8<Video> videos  = new ArrayList8<>();
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    public RandomStrategy(DataBundle data) {
        super(data);

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
