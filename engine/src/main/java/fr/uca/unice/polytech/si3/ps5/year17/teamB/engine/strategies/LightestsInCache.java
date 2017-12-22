package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.Comparator;

/**
 <hr>
 <h2>Fist in Strategy :</h2>
 <h3>Puts the lightests videos in cache only if those videos are requested by the linked endpoints</h3>
 <hr>
 */
public class LightestsInCache extends Strategy
{
    private ArrayList8<Video> videos;
    private ArrayList8<Video> videoToRemove;

    /**
     <hr>
     <h2>Default Constructor of this Strategy<br>
     <br>
     See {@link Strategy#Strategy(DataBundle, int)}
     </h2>
     <hr>

     @param data The DataBundle to apply this Strategy on.
     */
    public LightestsInCache (DataBundle data)
    {
        super(data, 5);
        videos = data.getVideos();
        // Sort the videos list by size (ascending order)
        videos.sort(Comparator.comparing(Video::getSize));
        this.videoToRemove = new ArrayList8<>();
    }

    /**
     <hr>
     <h2>Implementation of the apply method</h2>
     <h3>Puts the lightests videos in cache only if those videos are requested by the linked endpoints</h3>
     <hr>
     */
    @Override
    public void apply ()
    {
        ArrayList8<Video> cpy = new ArrayList8<>(videos);
        for (Cache cache : this.data.getCaches())
        {
            for (Video video : cpy)
            {
                if (!cache.getVideos().contains(video) && isRequestedByEndPoint(video, cache) && cache.addVideo(video))
                {
                    videoToRemove.add(video);
                }
            }
            cpy.removeAll(videoToRemove);
        }
    }
}
