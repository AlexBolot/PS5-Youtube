package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.List;

public class FirstInStrategy extends Strategy {

    private List<Video> videos;

    public FirstInStrategy(List<Connexion> connexions, List<Cache> caches, List<EndPoint> endPoints, List<Video> videos) {
        super(connexions, caches, endPoints);
        this.videos = videos;
    }

    @Override
    public void apply() {
        for (Video video : videos) {
            for (Cache cache : this.caches) {
                if (cache.getSize() > video.getSize() && !cache.getVideos().contains(video)) cache.addVideo(video);
            }
        }
    }
}
