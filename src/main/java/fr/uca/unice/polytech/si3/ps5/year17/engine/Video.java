package fr.uca.unice.polytech.si3.ps5.year17.engine;

public class Video {

    private int id = 0;
    private int size = 0;

    public Video(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Video " + "id = " + id + ", size=" + size;
    }
}
