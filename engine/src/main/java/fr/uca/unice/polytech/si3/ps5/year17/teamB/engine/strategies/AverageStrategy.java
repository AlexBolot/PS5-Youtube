package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Connection;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class AverageStrategy extends Strategy {

    private ArrayList8<Video> videos  = new ArrayList8<>();
    private ArrayList8<Video> videoToRemove = new ArrayList8<>();

    public AverageStrategy(DataBundle data) {
        super(data);
        videos = data.getVideos();
    }

    @Override
    public void apply() {

        HashMap<Integer, Integer> cachesLatencies = new HashMap<>();
        LinkedHashMap<Integer, Integer> sortedCachesLatencies;
        ArrayList8<Integer> listID = new ArrayList8<>();
        ArrayList8<Video> cpy = new ArrayList8<>();
        cpy.addAll(videos);

        for (Cache c : data.getCaches()) {
            int latenciesAVG;
            int latencies = 0;
            int latenciesNb = 0;
            for (Connection co : data.getConnections()){
                if(co.getIdCache() == c.getId()) {
                    latencies += co.getLatency();
                    latenciesNb ++;
                }
            }
            latenciesAVG = latencies / latenciesNb;
            cachesLatencies.put(c.getId(), latenciesAVG);
        }

        sortedCachesLatencies = cachesLatencies.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        Set keys = sortedCachesLatencies.keySet();
        listID.addAll(keys);
        for (int i : listID){
            for (int j = 0; j < cpy.size(); ++j) {
                if (!data.getCaches().get(i).getVideos().contains(data.getVideos().get(j))
                        && isRequestedByEndPoint(data.getVideos().get(j), data.getCaches().get(i))
                        && data.getCaches().get(i).addVideo(data.getVideos().get(j))) {
                    videoToRemove.add(data.getVideos().get(j));
                }
            }
            cpy.removeAll(videoToRemove);
        }

    }
}
