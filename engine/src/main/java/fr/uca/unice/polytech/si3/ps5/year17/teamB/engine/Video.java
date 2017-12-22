package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

/**
 <pre>
  _    _      _ _         _   _                     _
 | |  | |    | | |       | | | |                   | |
 | |__| | ___| | | ___   | |_| |__   ___ _ __ ___  | |
 |  __  |/ _ \ | |/ _ \  | __| '_ \ / _ \ '__/ _ \ | |
 | |  | |  __/ | | (_) | | |_| | | |  __/ | |  __/ |_|
 |_|  |_|\___|_|_|\___/   \__|_| |_|\___|_|  \___| (_)

 </pre>
 */
public class Video
{

    private int id   = 0;
    private int size = 0;

    /**
     Video Constructor

     @param id   The Video ID
     @param size The Video size
     */
    public Video (int id, int size)
    {
        this.id = id;
        this.size = size;
    }

    /**
     Getter for the Video Id

     @return The Video ID
     */
    public int getId ()
    {
        return id;
    }

    /**
     Setter for the Video ID

     @param id The Video ID
     */
    public void setId (int id)
    {
        this.id = id;
    }

    /**
     Getter for the Video size

     @return The Video size
     */
    public int getSize ()
    {
        return size;
    }

    /**
     Setter for the Video size

     @param size The Video size
     */
    public void setSize (int size)
    {
        this.size = size;
    }

    /**
     ToString method

     @return A String representation of the current Object and it's attributes
     */
    @Override
    public String toString ()
    {
        return "Video " + "id = " + id + ", size = " + size;
    }

    /**
     Equals method

     @param object
     @return true if the video is the same as the video in parameter, false otherwise,
     and false if the object is null or not a video
     */
    @Override
    public boolean equals (Object object)
    {
        if (!(object instanceof Video) || object.equals(null)) return false;
        Video video = (Video) object;
        if (video.id == this.id) return true;
        return false;
    }
}
