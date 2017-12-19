package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

/**
 Runs through all the caches and fills them with videos
 */
@SuppressWarnings ("unchecked")
public class FirstInStrategy extends Strategy
{
    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove;

    public FirstInStrategy (ArrayList8<Connexion> connexions,
                            ArrayList8<Cache> caches,
                            ArrayList8<EndPoint> endPoints,
                            ArrayList8<Video> videos)
    {
        super(connexions, caches, endPoints);
        this.videos = videos;
        this.videoToRemove = new ArrayList8<>();
    }

    public FirstInStrategy (Controller controller)
    {
        super(controller);
        this.videos = (ArrayList8<Video>) controller.getVideos().clone();
        this.videoToRemove = new ArrayList8<>();
    }

    @Override
    public void apply ()
    {
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);

        for (Cache cache : this.caches)
        {
            for (Video video : cpy)
            {
                if (cache.getSize() >= video.getSize() && !cache.getVideos().contains(video))
                {
                    cache.addVideo(video);
                    videoToRemove.add(video);
                }
            }
            cpy.removeAll(videoToRemove);
        }
    }
}
