package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

public class DataCenter
{
    private ArrayList8<Video> videos;

    /**
     DataCenter Constructor
     */
    public DataCenter ()
    {
        this(new ArrayList8<>());
    }

    /**
     DataCenter Constructor

     @param videos The videos stored in the DataCenter
     */
    public DataCenter (ArrayList8<Video> videos)
    {
        this.videos = videos;
    }

    public DataCenter (DataCenter dataCenter)
    {
        this.videos = dataCenter.getVideos().subList(video -> true);
    }

    /**
     Getter for the videos

     @return The video store in the DataCenter
     */
    public ArrayList8<Video> getVideos ()
    {
        return videos;
    }

    /**
     Setter for the video in the DataCenter

     @param videos The video Of the Datacenter
     */
    public void setVideos (ArrayList8<Video> videos)
    {
        this.videos = videos;
    }

    /**
     ToString method

     @return A String representation of the current Object and it's attributes
     */
    @Override
    public String toString ()
    {
        String txt = "Videos dans le DataCenter : \n";
        for (Video i : videos)
        {
            txt = txt + "- " + i.getId() + "\n";
        }
        return txt;
    }
}
