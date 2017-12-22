package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

/**
 <hr>
 <h2>ProbaTegy :</h2>
 <h3>Puts random videos in random caches</h3>
 <hr>
 */
public class RandomStrategy extends Strategy
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
    public RandomStrategy (DataBundle data)
    {
        super(data, 2);
        videos = data.getVideos();
        videoToRemove = new ArrayList8<>();
    }

    /**
     <hr>
     <h2>Implementation of the apply method</h2>
     <h3>Puts random videos in random caches</h3>
     <hr>
     */
    @Override
    public void apply ()
    {
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);
        for (Cache cache : data.getCaches())
        {
            for (int i = 0; i < cpy.size(); ++i)
            {
                Video randVid = cpy.getRandom();
                if (cache.getSize() >= randVid.getSize() && !cache.getVideos().contains(randVid))
                {
                    cache.addVideo(randVid);
                    videoToRemove.add(randVid);
                }
            }
            cpy.removeAll(videoToRemove);
        }

    }
}
