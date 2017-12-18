package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

public class EndPoint
{

    private int               id                 = 0;
    private ArrayList8<Query> queries            = new ArrayList8<>();
    private int               dataCenterLatency  = 0;
    private int               numberOfConnection = 0;

    public EndPoint (int id, ArrayList8<Query> queries, int dataCenterLatency, int numberOfConnection)
    {
        this.id = id;
        this.queries = queries;
        this.dataCenterLatency = dataCenterLatency;
        this.numberOfConnection = numberOfConnection;
    }

    public int getId ()
    {
        return id;
    }

    public void setId (int id)
    {
        this.id = id;
    }

    public ArrayList8<Query> getWantedVideos ()
    {
        return queries;
    }

    public void setWantedVideos (ArrayList8<Query> wantedVideos)
    {
        this.queries = wantedVideos;
    }

    public int getDataCenterLatency ()
    {
        return dataCenterLatency;
    }

    public void setDataCenterLatency (int dataCenterLatency)
    {
        this.dataCenterLatency = dataCenterLatency;
    }

    public int getNumberOfConnection ()
    {
        return numberOfConnection;
    }

    public void setNumberOfConnection (int numberOfConnection)
    {
        this.numberOfConnection = numberOfConnection;
    }

    @Override
    public String toString ()
    {
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
