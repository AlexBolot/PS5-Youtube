package fr.uca.unice.polytech.si3.ps5.year17.engine;

import java.util.HashMap;
import java.util.Map;

public class EndPoint {

    private int id = 0;
    private HashMap<Integer, Integer> wantedVideos = new HashMap<Integer, Integer>();

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

    @Override
    public String toString() {
        String txt = "EndPoint " + "id = " + id + ", videos voulues :\n";
        for(Map.Entry<Integer, Integer> entry : wantedVideos.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            txt = txt +"- " + key + " voulue " + value + " fois\n";
        }
        return txt;
    }
}
