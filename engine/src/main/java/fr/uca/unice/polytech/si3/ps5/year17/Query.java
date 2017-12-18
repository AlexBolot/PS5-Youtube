package fr.uca.unice.polytech.si3.ps5.year17;

public class Query
{
    private int      numberOfRequests;
    private Video    video;

    public Query (int numberOfRequests, Video video)
    {
        this.numberOfRequests = numberOfRequests;
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

    public Video getVideo ()
    {
        return video;
    }

    public void setVideo (Video video)
    {
        this.video = video;
    }
}
