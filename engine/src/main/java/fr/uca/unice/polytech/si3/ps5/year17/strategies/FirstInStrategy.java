package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.List;

public class FirstInStrategy extends Strategy {

    public FirstInStrategy(List<Connexion> connexions, List<Cache> caches, List<EndPoint> endPoints) {
        super(connexions, caches, endPoints);
    }

    @Override
    public void apply() {
        for (Cache cache : this.caches) {
            for (EndPoint endPoint : endPoints) {
                for (Query query : endPoint.getQueries()) {
                    if (cache.getSize() > query.getVideo().getSize() && !cache.getVideos().contains(query.getVideo())) cache.addVideo(query.getVideo());
                }
            }
        }
    }
}
