package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

public class DataBundle {

    private ArrayList8<Connexion> connexions;

    private ArrayList8<Cache> caches;

    private ArrayList8<Video> videos;

    private ArrayList8<EndPoint> endPoints;

    private DataCenter dataCenter;

    /**
     * TODO doc
     *
     * @param connexions
     * @param caches
     * @param videos
     * @param endPoints
     * @param dataCenter
     */
    public DataBundle(ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<Video> videos, ArrayList8<EndPoint> endPoints, DataCenter dataCenter) {
        this.connexions = connexions;
        this.caches = caches;
        this.videos = videos;
        this.endPoints = endPoints;
        this.dataCenter = dataCenter;
    }

    public ArrayList8<Connexion> getConnexions() {
        return connexions;
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
