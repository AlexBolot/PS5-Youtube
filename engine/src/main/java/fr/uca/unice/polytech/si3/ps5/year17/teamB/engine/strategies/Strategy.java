package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.*;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 <pre>
   _____   _                       _   _            _     _       _                                     _
  |_   _| | |                     | | | |          | |   (_)     | |                                   | |
    | |   | |__   __ ___   _____  | |_| |__   ___  | |__  _  __ _| |__   __ _ _ __ ___  _   _ _ __   __| |
    | |   | '_ \ / _` \ \ / / _ \ | __| '_ \ / _ \ | '_ \| |/ _` | '_ \ / _` | '__/ _ \| | | | '_ \ / _` |
   _| |_  | | | | (_| |\ V /  __/ | |_| | | |  __/ | | | | | (_| | | | | (_| | | | (_) | |_| | | | | (_| |
  |_____| |_| |_|\__,_| \_/ \___|  \__|_| |_|\___| |_| |_|_|\__, |_| |_|\__, |_|  \___/ \__,_|_| |_|\__,_|
                                                             __/ |       __/ |
                                                            |___/       |___/
  </pre>
 */
@SuppressWarnings ("unchecked")
public abstract class Strategy
{
    protected DataBundle data;
    protected int        stratID;

    /**
     <hr>
     <h2>Default Constructor of the Strategy</h2>
     <hr>

     @param data    The DataBundle to apply the strategy on
     @param stratID The ID of the strategy (we try to sort them by scoring)
     */
    public Strategy (DataBundle data, int stratID)
    {
        Objects.requireNonNull(data, "data param is null");

        this.data = data;
        this.stratID = stratID;
    }

    /**
     <hr>
     <h2>Applies this Strategy on its DataBundle</h2>
     <h3>Note : check the DataBundle before and after to see the effects of this strategy</h3>
     <hr>
     */
    public abstract void apply ();

    /**
     <hr>
     <h2>ToString method</h2>
     <hr>

     @return A representation of this Strategy in a String form
     */
    @Override
    public String toString ()
    {
        long usedCache = data.getCaches().stream().filter(cache -> !cache.getVideos().isEmpty()).count();
        StringBuilder builder = new StringBuilder();
        builder.append(usedCache).append('\n');
        for (Cache cache : data.getCaches())
        {
            if (cache.getVideos().isEmpty()) continue;
            builder.append(cache.getId());
            for (Video video : cache.getVideos()) builder.append(' ').append(video.getId());
            builder.append('\n');
        }
        return builder.toString();
    }

    /**
     <hr>
     <h2>Checks if the [video] param is requested by any EndPoint connected to the [cache] param</h2>
     <hr>

     @param video Video looked for
     @param cache Cache parameter (didn't know how to explain)
     @return true if the video is indeed needed by the endpoint
     */
    protected boolean isRequestedByEndPoint (Video video, Cache cache)
    {
        ArrayList8<EndPoint> linkedEndpoints = new ArrayList8<>();
        for (EndPoint endpoint : data.getEndPoints())
        {
            for (Connection connection : data.getConnections())
            {
                if (connection.getIdCache() == cache.getId() && connection.getIdEndPoint() == endpoint.getId())
                {
                    linkedEndpoints.add(endpoint);
                }
            }
        }

        for (EndPoint endpoint : linkedEndpoints)
        {
            for (Query query : endpoint.getQueries())
            {
                if (video.getId() == query.getVideo().getId()) return true;
            }
        }
        return false;
    }

    /**
     <hr>
     <h3>Getter for the Strategy ID</h3>
     <hr>

     @return The ID of the Strategy
     */
    public int getStratID ()
    {
        return stratID;
    }

    /**
     <hr>
     <h3>Getter for the DataBundle</h3>
     <hr>

     @return The DataBundle of the Strategy
     */
    public DataBundle getData ()
    {
        return data;
    }

}
