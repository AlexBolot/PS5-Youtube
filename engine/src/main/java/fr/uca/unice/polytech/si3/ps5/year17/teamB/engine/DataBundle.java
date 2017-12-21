package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

public class DataBundle {

    private ArrayList8<Connection> connections;

    private ArrayList8<Cache> caches;

    private ArrayList8<Video> videos;

    private ArrayList8<EndPoint> endPoints;

    private DataCenter dataCenter;

    /**
     * Creates a bundle of data from the objects passed in parameter and saves their values
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

    /**
     * Creates a bundle of data from the DataBundle object passed in parameter and saves their values
     * @param data
     */
    public DataBundle(DataBundle data) {
        this.connections = data.getConnections();
        this.caches = data.getCaches();
        this.videos = data.getVideos();
        this.endPoints = data.getEndPoints();
        this.dataCenter = data.getDataCenter();
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

    public boolean isEmpty() {
        return this.caches.isEmpty() && this.connections.isEmpty() && this.endPoints.isEmpty() && this.videos.isEmpty();
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (!(o instanceof DataBundle)) return false;

        DataBundle that = (DataBundle) o;

        if (connections != null ? !connections.equals(that.connections) : that.connections != null) return false;
        if (caches != null ? !caches.equals(that.caches) : that.caches != null) return false;
        if (videos != null ? !videos.equals(that.videos) : that.videos != null) return false;
        if (endPoints != null ? !endPoints.equals(that.endPoints) : that.endPoints != null) return false;
        //return dataCenter != null ? dataCenter.equals(that.dataCenter) : that.dataCenter == null;

        return true;
    }
}
