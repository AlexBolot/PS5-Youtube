package fr.uca.unice.polytech.si3.ps5.year17.engine;

import java.util.ArrayList;

public class DataCenter {

    private ArrayList<Integer> videos;

    public DataCenter(ArrayList<Integer> videos) {
        this.videos = videos;
    }

    public ArrayList<Integer> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Integer> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        String txt = "Videos dans le DataCenter = \n";
        for (Integer i : videos)
        {
            txt = txt +"- " + i + "\n";
        }
        return txt;
    }
}
