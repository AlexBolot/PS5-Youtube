package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;


import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    /**
     * Tests for file me_at_the_zoo.in
     */
    @Test
    void testParsingMeAtTheZoo() {
        Parser parser = new Parser();
        InputStream in = this.getClass().getResourceAsStream("/me_at_the_zoo.in");
        parser.parse(in);
        DataBundle dataBundle = new DataBundle(parser.getData());
        ArrayList8<Connection> connections = dataBundle.getConnections();
        ArrayList8<Video> videos = dataBundle.getVideos();
        ArrayList8<EndPoint> endpoints = dataBundle.getEndPoints();
        ArrayList8<Cache> caches = dataBundle.getCaches();
        DataCenter dataCenter = dataBundle.getDataCenter();

        assertEquals(videos.size(), parser.getNumberOfVideos());
        assertEquals(videos.size(), dataCenter.getVideos().size());
        assertEquals(endpoints.size(), parser.getNumberOfEndpoints());
        assertEquals(caches.size(), parser.getNumberOfCacheServers());

        assertEquals(1013, endpoints.get(0).getDataCenterLatency());
        assertEquals(365, endpoints.get(endpoints.size()-1).getDataCenterLatency());

        assertEquals(5, endpoints.get(4).getNumberOfConnection());

        caches.sort(Comparator.comparingInt(Cache::getId));
        assertEquals(0, caches.get(0).getId());
        assertEquals(1, caches.get(1).getId());
        assertEquals(2, caches.get(2).getId());
        assertEquals(3, caches.get(3).getId());
        assertEquals(4, caches.get(4).getId());
        assertEquals(5, caches.get(5).getId());
        assertEquals(6, caches.get(6).getId());
        assertEquals(7, caches.get(7).getId());
        assertEquals(8, caches.get(8).getId());
        assertEquals(9, caches.get(9).getId());

        assertEquals(170, connections.get(0).getLatency());
        assertEquals(22, connections.get(1).getLatency());
        assertEquals(224, connections.get(2).getLatency());
        assertEquals(202, connections.get(5).getLatency());

        videos.sort(Comparator.comparingInt(Video::getId));
        assertEquals(0, videos.get(0).getId());
        assertEquals(16, videos.get(16).getId());
        assertEquals(34, videos.get(34).getId());
        assertEquals(99, videos.get(99).getId());

        endpoints.sort(Comparator.comparingInt(EndPoint::getId));
        assertEquals(0, endpoints.get(0).getId());
        assertEquals(2, endpoints.get(2).getId());
        assertEquals(4, endpoints.get(4).getId());
        assertEquals(9, endpoints.get(9).getId());
    }

    /**
     * kittens.in.txt
     */
    @Test
    void testParsingKittens() {
        Parser parser = new Parser();
        InputStream in = this.getClass().getResourceAsStream("/kittens.in.txt");
        parser.parse(in);
        DataBundle dataBundle = new DataBundle(parser.getData());
        ArrayList8<Connection> connections = dataBundle.getConnections();
        ArrayList8<Video> videos = dataBundle.getVideos();
        ArrayList8<EndPoint> endpoints = dataBundle.getEndPoints();
        ArrayList8<Cache> caches = dataBundle.getCaches();
        DataCenter dataCenter = dataBundle.getDataCenter();

        assertEquals(videos.size(), parser.getNumberOfVideos());
        assertEquals(videos.size(), dataCenter.getVideos().size());
        assertEquals(endpoints.size(), parser.getNumberOfEndpoints());
        assertEquals(caches.size(), parser.getNumberOfCacheServers());

        assertEquals(1645, endpoints.get(0).getDataCenterLatency());
        assertEquals(2157, endpoints.get(endpoints.size()-1).getDataCenterLatency());

        assertEquals(295, endpoints.get(4).getNumberOfConnection());

        caches.sort(Comparator.comparingInt(Cache::getId));
        assertEquals(0, caches.get(0).getId());
        assertEquals(1, caches.get(1).getId());
        assertEquals(2, caches.get(2).getId());
        assertEquals(3, caches.get(3).getId());
        assertEquals(4, caches.get(4).getId());
        assertEquals(5, caches.get(5).getId());
        assertEquals(6, caches.get(6).getId());
        assertEquals(7, caches.get(7).getId());
        assertEquals(8, caches.get(8).getId());
        assertEquals(9, caches.get(9).getId());

        assertEquals(41, connections.get(0).getLatency());
        assertEquals(497, connections.get(1).getLatency());
        assertEquals(337, connections.get(2).getLatency());
        assertEquals(61, connections.get(5).getLatency());
        assertEquals(85, connections.get(391).getLatency());
        assertEquals(122, connections.get(887).getLatency());

        videos.sort(Comparator.comparingInt(Video::getId));
        assertEquals(0, videos.get(0).getId());
        assertEquals(16, videos.get(16).getId());
        assertEquals(34, videos.get(34).getId());
        assertEquals(99, videos.get(99).getId());

        endpoints.sort(Comparator.comparingInt(EndPoint::getId));
        assertEquals(0, endpoints.get(0).getId());
        assertEquals(2, endpoints.get(2).getId());
        assertEquals(4, endpoints.get(4).getId());
        assertEquals(9, endpoints.get(9).getId());
    }

    /**
     * Checks for the extension of files
     */
    @Test
    void testParsingException(){
        Parser parser = new Parser();
        InputStream stream = this.getClass().getResourceAsStream("/wrongFileForTests.ini.txt.wmv");
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(stream);
        });

        String path_2 = this.getClass().getResource("/wrongFileForTests.mp3").getPath();
        assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(path_2);
        });

        String path_3 = this.getClass().getResource("/me_at_the_zoo.in").getPath();
        parser.parse(path_3);
        assertFalse(parser.getData().isEmpty());

    }
}