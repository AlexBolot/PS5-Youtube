package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

/**
 <hr>
 <h2>Fist in Strategy :</h2>
 <h3>Tries to put the videos in the caches as they arrive (no real optimal work here)</h3>
 <hr>
 */
@SuppressWarnings ("unchecked")
public class FirstInStrategy extends Strategy
{
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
    public FirstInStrategy (DataBundle data)
    {
        super(data, 4);
        this.videoToRemove = new ArrayList8<>();
    }

    /**
     <hr>
     <h2>Implementation of the apply method</h2>
     <h3>Tries to put the videos in the caches as they arrive (no real optimal work here)</h3>
     <hr>
     */
    @Override
    public void apply ()
    {
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(data.getVideos());

        for (Cache cache : data.getCaches())
        {
            for (Video video : cpy)
            {
                if (!cache.getVideos().contains(video) && cache.addVideo(video))
                {
                    videoToRemove.add(video);
                }
            }

            cpy.removeAll(videoToRemove);
        }
    }
}
