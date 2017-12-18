package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.ArrayList;

public class DataCenter {

    private ArrayList8<Video> videos = new ArrayList8<>();

    public DataCenter () {}

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos (ArrayList8<Video> videos)
    {
        this.videos = videos;
    }

    @Override
    public String toString() {
        String txt = "Videos dans le DataCenter : \n";
        for (Video i : videos)
        {
            txt = txt +"- " + i.getId() + "\n";
        }
        return txt;
    }
}
