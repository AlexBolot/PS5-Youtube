package fr.uca.unice.polytech.si3.ps5.year17;

public class Connection {

    private int idCache;
    private int idEndPoint;
    private int latency;

    /**
     * Connection Constructor
     *
     * @param idCache       The ID where the video are stocked
     * @param idEndPoint    The ID where the video will be sent
     * @param latency       The speed to get the video to the EndPoint
     */
    public Connection(int idCache, int idEndPoint, int latency) {
        this.idCache = idCache;
        this.idEndPoint = idEndPoint;
        this.latency = latency;
    }

    /**
     * Getter for the Cache ID
     *
     * @return The Cache ID
     */
    public int getIdCache() {
        return idCache;
    }

    /**
     * Setter for the Cache ID
     *
     * @param idCache The Cache ID
     */
    public void setIdCache(int idCache) {
        this.idCache = idCache;
    }

    /**
     * Getter for the EndPoint ID
     *
     * @return The EndPoint ID
     */
    public int getIdEndPoint() {
        return idEndPoint;
    }

    /**
     * Setter for the EndPoint ID
     *
     * @param idEndPoint The EndPoint ID
     */
    public void setIdEndPoint(int idEndPoint) {
        this.idEndPoint = idEndPoint;
    }

    /**
     * Getter for the latency of the connection
     *
     * @return The Connection latency
     */
    public int getLatency() {
        return latency;
    }

    /**
     * Setter for the connection latency
     *
     * @param latency The connection latency
     */
    public void setLatency(int latency) {
        this.latency = latency;
    }

    /**
     * ToString method
     *
     * @return A String representation of the current Object and it's attributes
     */
    @Override
    public String toString() {
        return "Connection " + "id du cache = " + idCache + ", id de l'endPoint = " + idEndPoint + ", latence = " + latency;
    }
}
