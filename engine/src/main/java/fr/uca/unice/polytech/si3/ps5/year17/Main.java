package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.ArrayList;

/**
 * @author Collet wa Mou Mosseiru
 * @version 1.0
 */

public class Main {

    public static void main(String args[]) {

        Video vid0 = new Video(0,50);
        Video vid1 = new Video(1,50);
        Video vid2 = new Video(2,80);
        Video vid3 = new Video(3,30);
        Video vid4 = new Video(4,110);

        DataCenter dc = new DataCenter();
        dc.getVideos().add(vid0);
        dc.getVideos().add(vid1);
        dc.getVideos().add(vid2);
        dc.getVideos().add(vid3);
        dc.getVideos().add(vid4);

        EndPoint endPoint0 = new EndPoint(0,1000,3);
        endPoint0.getWantedVideos().put(vid3,1500);
        endPoint0.getWantedVideos().put(vid4,500);
        endPoint0.getWantedVideos().put(vid1,1000);

        Cache ca0 = new Cache(0,10000);
        Cache ca1 = new Cache(1,10000);
        Cache ca2 = new Cache(2,10000);

        ca0.getVideos().add(vid2);
        ca1.getVideos().add(vid3);
        ca1.getVideos().add(vid1);
        ca2.getVideos().add(vid1);
        ca2.getVideos().add(vid0);

        Connexion co1 = new Connexion(0,0,100);
        Connexion co2 = new Connexion(1,0,300);
        Connexion co3 = new Connexion(2,0,200);

        ArrayList8<Connexion> connexions = new ArrayList8<>();
        connexions.add(co1);
        connexions.add(co2);
        connexions.add(co3);

        ArrayList8<Cache> caches = new ArrayList8<>();
        caches.add(ca0);
        caches.add(ca1);
        caches.add(ca2);

        ArrayList8<EndPoint> endPoints = new ArrayList8<>();
        endPoints.add(endPoint0);

        Controller controller = new Controller(connexions,caches,endPoints);

        System.out.println(controller.scoring());

    }

}
