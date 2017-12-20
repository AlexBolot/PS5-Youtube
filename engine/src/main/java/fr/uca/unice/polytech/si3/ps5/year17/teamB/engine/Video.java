package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

public class Video {

    private int id = 0;
    private int size = 0;

    /**
     * Video Constructor
     *
     * @param id    The Video ID
     * @param size  The Video size
     */
    public Video(int id, int size) {
        this.id = id;
        this.size = size;
    }

    /**
     * Getter for the Video Id
     *
     * @return The Video ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the Video ID
     *
     * @param id The Video ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the Video size
     *
     * @return The Video size
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter for the Video size
     *
     * @param size The Video size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * ToString method
     *
     * @return A String representation of the current Object and it's attributes
     */
    @Override
    public String toString() {
        return "Video " + "id = " + id + ", size = " + size;
    }
}
