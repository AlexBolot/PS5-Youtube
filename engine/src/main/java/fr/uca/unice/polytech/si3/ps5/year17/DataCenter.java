package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.List;

public class DataCenter {

    private List<Video> videos = new ArrayList8<>();

    /**
     * Getter for the videos
     *
     * @return The video store in the DataCenter
     */
    public List<Video> getVideos() {
        return videos;
    }

    /**
     * Setter for the video in the DataCenter
     *
     * @param videos The video Of the Datacenter
     */
    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    /**
     * ToString method
     *
     * @return A String representation of the current Object and it's attributes
     */
    @Override
    public String toString() {
        String txt = "Videos dans le DataCenter : \n";
        for (Video i : videos) {
            txt = txt + "- " + i.getId() + "\n";
        }
        return txt;
    }
}
