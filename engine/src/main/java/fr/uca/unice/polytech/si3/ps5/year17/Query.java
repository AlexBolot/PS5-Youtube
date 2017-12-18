package fr.uca.unice.polytech.si3.ps5.year17;

public class Query
{
    private int      numberOfRequests;
    private EndPoint endPoint;
    private Video    video;

    public Query (int numberOfRequests, EndPoint endPoint, Video video)
    {
        this.numberOfRequests = numberOfRequests;
        this.endPoint = endPoint;
        this.video = video;
    }

    public int getNumberOfRequests ()
    {
        return numberOfRequests;
    }

    public void setNumberOfRequests (int numberOfRequests)
    {
        this.numberOfRequests = numberOfRequests;
    }

    public EndPoint getEndPoint ()
    {
        return endPoint;
    }

    public void setEndPoint (EndPoint endPoint)
    {
        this.endPoint = endPoint;
    }

    public Video getVideo ()
    {
        return video;
    }

    public void setVideo (Video video)
    {
        this.video = video;
    }
}
