package fr.uca.unice.polytech.si3.ps5.year17;

public class Query {

    private int numberOfRequests;
    private Video video;

    /**
     * Query Constructor
     *
     * @param numberOfRequests  The number of request
     * @param video             The Video requested
     */
    public Query(int numberOfRequests, Video video) {
        this.numberOfRequests = numberOfRequests;
        this.video = video;
    }

    /**
     * Getter for the number of time a Video is requested
     *
     * @return The number a time a Video is requested by a EndPoint
     */
    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    /**
     * Setter for the number of time a Video is requested by a EndPoint
     *
     * @param numberOfRequests The number of time a Video is requested by a EndPoint
     */
    public void setNumberOfRequests(int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

    /**
     * Getter for the Video requested
     *
     * @return The Video requested
     */
    public Video getVideo() {
        return video;
    }

    /**
     * Setter for the Video requested
     *
     * @param video The Video requested
     */
    public void setVideo(Video video) {
        this.video = video;
    }
}
