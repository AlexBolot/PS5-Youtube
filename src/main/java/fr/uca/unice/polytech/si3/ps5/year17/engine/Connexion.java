package fr.uca.unice.polytech.si3.ps5.year17.engine;

public class Connexion {

    private int idCache = 0;
    private int idEndPoint = 0;
    private int latency = 0;

    public Connexion(int idCache, int idEndPoint, int latency) {
        this.idCache = idCache;
        this.idEndPoint = idEndPoint;
        this.latency = latency;
    }

    public int getIdCache() {
        return idCache;
    }

    public void setIdCache(int idCache) {
        this.idCache = idCache;
    }

    public int getIdEndPoint() {
        return idEndPoint;
    }

    public void setIdEndPoint(int idEndPoint) {
        this.idEndPoint = idEndPoint;
    }

    public int getLatency() {
        return latency;
    }

    public void setLatency(int latency) {
        this.latency = latency;
    }

    @Override
    public String toString() {
        return "Connexion " + "id du cache = " + idCache + ", id de l'endPoint = " + idEndPoint + ", latence = " + latency;
    }
}
