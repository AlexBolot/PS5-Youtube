package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.Video;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.Random;

public class RandomStrategy extends Strategy {

    private ArrayList8<Video> videos  = new ArrayList8<>();
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    public RandomStrategy(ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints, ArrayList8<Video> videos) {
        super(connexions, caches, endPoints);
        this.caches = caches;
        this.videos = videos;

    }

    @Override
    public void apply() {

        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);
        int randNum;
        for (Cache cache : this.caches) {
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
