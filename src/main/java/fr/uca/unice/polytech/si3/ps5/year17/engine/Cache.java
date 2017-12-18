package fr.uca.unice.polytech.si3.ps5.year17.engine;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.ArrayList;

public class Cache {

    private int id = 0;
    private ArrayList8<Video> videos = new ArrayList8<>();
    private int size = 0;

    public Cache(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos (ArrayList8<Video> videos)
    {
        this.videos = videos;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        String txt = "Cache " + "id = " + id + ", taille = " + size + ", videos :\n";
        for (Video i : videos)
        {
            txt = txt +"- " + i.getId() + "\n";
        }
        return txt;
    }
}
