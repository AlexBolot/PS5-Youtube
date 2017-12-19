package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FirstInStrategy extends Strategy {

    private List<Video> videos;

    private List<Video> videoToRemove = new ArrayList<>();

    public FirstInStrategy(List<Connexion> connexions, List<Cache> caches, List<EndPoint> endPoints, List<Video> videos) {
        super(connexions, caches, endPoints);
        this.videos = videos;
    }

    @Override
    public void apply() {
        for (Cache cache : this.caches) {
            for (Video video : this.videos) {
                if (cache.getSize() > video.getSize() && !cache.getVideos().contains(video)) {
                    cache.addVideo(video);
                    videoToRemove.add(video);
                }
            }
            videos.removeAll(videoToRemove);
        }
    }
}
