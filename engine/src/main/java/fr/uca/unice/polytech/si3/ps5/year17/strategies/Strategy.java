package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

@SuppressWarnings("unchecked")
public abstract class Strategy {
    protected DataBundle data;

    /**
     * Constructor for a strategy
     */
    public Strategy(DataBundle data) {
        this.data = data;
    }

    /**
     * Apply the strategy
     */
    public abstract void apply();

    public DataBundle getData() {
        return data;
    }

    @Override
    public String toString() {
        long usedCache = data.getCaches().stream().filter(cache -> !cache.getVideos().isEmpty()).count();
        StringBuilder builder = new StringBuilder();
        builder.append(usedCache).append('\n');
        for (Cache cache : data.getCaches()) {
            if (cache.getVideos().isEmpty()) continue;
            builder.append(cache.getId());
            for (Video video : cache.getVideos()) builder.append(' ').append(video.getId());
            builder.append('\n');
        }
        return builder.toString();
    }
}
