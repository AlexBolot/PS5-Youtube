package fr.uca.unice.polytech.si3.ps5.year17.engine;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.ArrayList;

public class DataCenter {

    private ArrayList8<Integer> videos = new ArrayList8<>();

    public DataCenter () {}

    public ArrayList<Integer> getVideos() {
        return videos;
    }

    public void setVideos (ArrayList8<Integer> videos)
    {
        this.videos = videos;
    }

    @Override
    public String toString() {
        String txt = "Videos dans le DataCenter : \n";
        for (Integer i : videos)
        {
            txt = txt +"- " + i + "\n";
        }
        return txt;
    }
}
