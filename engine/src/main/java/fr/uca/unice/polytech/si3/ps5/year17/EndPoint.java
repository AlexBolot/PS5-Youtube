package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

public class EndPoint {

    private int id;
    private ArrayList8<Query> queries;
    private int dataCenterLatency;
    private int numberOfConnection;

    /**
     * EndPoint Constructor
     *
     * @param id                    The EndPoint ID
     * @param queries               The Queries of that this EndPoint requested
     * @param dataCenterLatency     The
     * @param numberOfConnection
     */
    public EndPoint(int id, ArrayList8<Query> queries, int dataCenterLatency, int numberOfConnection) {
        this.id = id;
        this.queries = queries;
        this.dataCenterLatency = dataCenterLatency;
        this.numberOfConnection = numberOfConnection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList8<Query> getQueries() {
        return queries;
    }

    public void setQueries(ArrayList8<Query> queries) {
        this.queries = queries;
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

    /**
     * ToString method
     *
     * @return A String representation of the current Object and it's attributes
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("EndPoint ").append("id = ").append(id).append(", videos voulues :\n");

        queries.forEach(query -> str.append("- ")
                .append(query.getVideo().getId())
                .append(" voulue ")
                .append(query.getNumberOfRequests())
                .append(" fois\n"));

        return str.toString();
    }
}
