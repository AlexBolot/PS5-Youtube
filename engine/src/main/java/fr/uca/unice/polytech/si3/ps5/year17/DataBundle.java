package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

public class DataBundle {

    private ArrayList8<Connection> connections;

    private ArrayList8<Cache> caches;

    private ArrayList8<Video> videos;

    private ArrayList8<EndPoint> endPoints;

    private DataCenter dataCenter;

    /**
     * TODO doc
     *
     * @param connections
     * @param caches
     * @param videos
     * @param endPoints
     * @param dataCenter
     */
    public DataBundle(ArrayList8<Connection> connections, ArrayList8<Cache> caches, ArrayList8<Video> videos, ArrayList8<EndPoint> endPoints, DataCenter dataCenter) {
        this.connections = connections;
        this.caches = caches;
        this.videos = videos;
        this.endPoints = endPoints;
        this.dataCenter = dataCenter;
    }

    public ArrayList8<Connection> getConnections() {
        return connections;
    }

    public ArrayList8<Cache> getCaches() {
        return caches;
    }

    public ArrayList8<Video> getVideos() {
        return videos;
    }

    public ArrayList8<EndPoint> getEndPoints() {
        return endPoints;
    }

    public DataCenter getDataCenter() {
        return dataCenter;
    }
}
