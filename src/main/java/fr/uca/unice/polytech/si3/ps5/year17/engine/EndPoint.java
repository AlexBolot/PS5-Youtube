package fr.uca.unice.polytech.si3.ps5.year17.engine;

import java.util.HashMap;

public class EndPoint {

    private int id = 0;
    private HashMap<Integer, Integer> wantedVideos;

    public EndPoint(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, Integer> getWantedVideos() {
        return wantedVideos;
    }

    public void setWantedVideos(HashMap<Integer, Integer> wantedVideos) {
        this.wantedVideos = wantedVideos;
    }
}
