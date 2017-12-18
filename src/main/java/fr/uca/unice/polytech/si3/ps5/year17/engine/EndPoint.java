package fr.uca.unice.polytech.si3.ps5.year17.engine;

import java.util.HashMap;
import java.util.Map;

public class EndPoint {

    private int id = 0;
    private HashMap<Video, Integer> wantedVideos = new HashMap<>();
    private int dataCenterLatency = 0;
    private int numberOfConnection = 0;

    public EndPoint(int id, int dataCenterLatency, int numberOfConnection) {
        this.id = id;
        this.dataCenterLatency = dataCenterLatency;
        this.numberOfConnection = numberOfConnection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Video, Integer> getWantedVideos() {
        return wantedVideos;
    }

    public void setWantedVideos(HashMap<Video, Integer> wantedVideos) {
        this.wantedVideos = wantedVideos;
    }

    public int getDataCenterLatency() {
        return dataCenterLatency;
    }

    public void setDataCenterLatency(int dataCenterLatency) {
        this.dataCenterLatency = dataCenterLatency;
    }

    public int getNumberOfConnection() {
        return numberOfConnection;
    }

    public void setNumberOfConnection(int numberOfConnection) {
        this.numberOfConnection = numberOfConnection;
    }

    @Override
    public String toString() {
        String txt = "EndPoint " + "id = " + id + ", videos voulues :\n";
        for(Map.Entry<Video, Integer> entry : wantedVideos.entrySet()) {
            Video key = entry.getKey();
            int value = entry.getValue();

            txt = txt +"- " + key.getId() + " voulue " + value + " fois\n";
        }
        return txt;
    }
}
