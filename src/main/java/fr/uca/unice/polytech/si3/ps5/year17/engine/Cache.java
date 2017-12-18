package fr.uca.unice.polytech.si3.ps5.year17.engine;

import java.util.ArrayList;

public class Cache {

    private int id = 0;
    private ArrayList<Integer> videos;
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

    public ArrayList<Integer> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Integer> videos) {
        this.videos = videos;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String toString() {
        String txt = "Cache " + "id = " + id + ", size=" + size + ", video =\n";
        for (Integer i : videos)
        {
            txt = txt +"- " + i + "\n";
        }
        return txt;
    }
}
