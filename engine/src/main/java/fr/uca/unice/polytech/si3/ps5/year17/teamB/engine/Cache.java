package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.Objects;

public class Cache {

    private int id;
    private ArrayList8<Video> videos = new ArrayList8<>();
    private int size;

    /**
     * Cache Constructor
     *
     * @param id   The ID of the Cache
     * @param size The size of the Cache
     */
    public Cache(int id, int size) {
        this.id = id;
        this.size = size;
    }

    /**
     * Cache Constructor
     *
     * @param id     The ID of the Cache
     * @param size   The size of the Cache
     * @param videos The videos stored in the Cache
     */
    public Cache(int id, int size, ArrayList8<Video> videos) {
        this(id, size);
        this.videos = videos;
    }

    /**
     * Getter for the Cache ID
     *
     * @return The Cache ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the Cache ID
     *
     * @param id The Cache ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the video store on that Cache
     *
     * @return The video store on that Cache
     */
    public ArrayList8<Video> getVideos() {
        return videos;
    }

    /**
     * Setter for the video store in that Cache
     *
     * @param videos The videos
     */
    public void setVideos(ArrayList8<Video> videos) {
        this.videos = videos;
    }

    /**
     * Getter for the size of the Cache
     *
     * @return The size of the Cache
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter for the size of the Cache
     *
     * @param size The size of the Cache
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Add a Video to the Cache server
     *
     * @param video The Video to add to the Cache server
     * @return If the Video has been correctly added (false if the size of the video is higher than the cache's)
     */
    public boolean addVideo(Video video) {
        if (video.getSize() > this.size) return false;
        this.size -= video.getSize();
        return this.videos.add(video);
    }

    /**
     * Equals method
     *
     * @param o The object to compare the attributes from
     * @return If the attribute of the parameter object are equals to the current object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cache)) return false;
        Cache cache = (Cache) o;
        return id == cache.id;
    }


    /**
     * ToString method
     *
     * @return A String representation of the current Object and it's attributes
     */
    @Override
    public String toString() {
        String txt = "Cache " + "id = " + id + ", taille = " + size + ", videos :\n";
        for (Video i : videos) {
            txt = txt + "- " + i.getId() + "\n";
        }
        return txt;
    }
}
