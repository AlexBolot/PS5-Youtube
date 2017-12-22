package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    ArrayList8<Connection> connexions = new ArrayList8<>();
    ArrayList8<Cache> caches = new ArrayList8<>();
    ArrayList8<EndPoint> endPoints = new ArrayList8<>();
    ArrayList8<Video> vids = new ArrayList8<>();
    DataCenter dc = new DataCenter();
    Controller controller;
    DataBundle dB;

    @BeforeEach
    void setUp() {

        Video vid0 = new Video(0,50);
        Video vid1 = new Video(1,50);
        Video vid2 = new Video(2,80);
        Video vid3 = new Video(3,30);
        Video vid4 = new Video(4,110);

        vids.add(vid0);
        vids.add(vid1);
        vids.add(vid2);
        vids.add(vid3);
        vids.add(vid4);

        dc.getVideos().addAll(vids);

        Query q0 = new Query(1500,vid3);
        Query q1 = new Query(500,vid4);
        Query q2 = new Query(1000,vid1);
        Query q3 = new Query(1000,vid0);

        ArrayList<Query> qList = new ArrayList<>();
        qList.add(q1);
        qList.add(q2);
        qList.add(q0);

        ArrayList<Query> qList2 = new ArrayList<>();
        qList2.add(q3);

        EndPoint endPoint0 = new EndPoint(0, qList,1000,3);
        EndPoint endPoint1 = new EndPoint(1, qList2,500,0);

        Cache ca0 = new Cache(0,100);
        Cache ca1 = new Cache(1,100);
        Cache ca2 = new Cache(2,100);

        ca0.getVideos().add(vid2);
        ca1.getVideos().add(vid3);
        ca1.getVideos().add(vid1);
        ca2.getVideos().add(vid1);
        ca2.getVideos().add(vid0);

        Connection co1 = new Connection(0,0,100);
        Connection co2 = new Connection(1,0,300);
        Connection co3 = new Connection(2,0,200);

        connexions.add(co1);
        connexions.add(co2);
        connexions.add(co3);

        caches.add(ca0);
        caches.add(ca1);
        caches.add(ca2);

        endPoints.add(endPoint0);
        endPoints.add(endPoint1);

        dB  = new DataBundle(connexions,caches,vids,endPoints,dc);

        controller = new Controller(null);

    }

    @Test
    void scoring() {

        assertEquals(462500, controller.scoring(dB));

    }

    @Test
    void generateOutput() {

        String dataPath = "C:\\Users\\user\\Desktop\\Result\\dataTest.out";
        String scorePath = "C:\\Users\\user\\Desktop\\Result\\dataTest.out";
        controller.generateOutput(dataPath, scorePath);

        File f = new File(dataPath);
        assertEquals(true, f.exists());
        File f2 = new File(scorePath);
        assertEquals(true, f2.exists());

    }

}