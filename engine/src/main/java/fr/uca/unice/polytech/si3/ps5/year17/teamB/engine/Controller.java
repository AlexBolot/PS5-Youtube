package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.Strategy;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {

    private Strategy strategy;

    public Controller(Strategy strategy) {
        this.strategy = strategy;
    }


    /**
     * @param data all the data, connections, endpoints, caches, videos and data center
     * @return the calculate score (number of request * (latency with the data center - latency with the cache))
     */
    public double scoring(DataBundle data) {
        double temp = 0;
        double temp2 = 0;
        double score = 0;

        for (EndPoint endPoint : data.getEndPoints()) {
            HashMap<Integer, Double> bestTimes = new HashMap<>();
            for (Connection connection : data.getConnections()) {
                if (endPoint.getId() == connection.getIdEndPoint()) {
                    for (Query query : endPoint.getQueries()) {
                        if (data.getCaches().get(connection.getIdCache()).getVideos().contains(query.getVideo())) {
                            int videoID = query.getVideo().getId();
                            double nbRequest = query.getNumberOfRequests();
                            double dataCenterLatency = endPoint.getDataCenterLatency();
                            double connexionLatency = connection.getLatency();

                            double totalGain = (nbRequest * (dataCenterLatency - connexionLatency));

                            if (!bestTimes.containsKey(videoID)) bestTimes.put(videoID, totalGain);
                            else if (bestTimes.get(videoID) < totalGain) bestTimes.put(videoID, totalGain);
                        }
                    }
                }
            }

            temp2 += endPoint.getQueries().stream().mapToInt(Query::getNumberOfRequests).sum();
            temp += bestTimes.keySet().stream().mapToDouble(bestTimes::get).sum();
        }

        score = temp / temp2;

        return Math.floor(score * 1000);
    }

    public void generateOutput(String dataPath, String scorePath) {

        strategy.apply();

        String result = strategy.toString();
        String score = scoring(strategy.getData()) + "";

//        System.out.println("Strategy : " + strategy.getClass().getSimpleName() + "\n");
//        System.out.println("Data Output : \n\n" + result + "\n");
//        System.out.println("Score : " + score + "\n");

        try (PrintWriter dataOut = new PrintWriter(dataPath, "UTF-8");
             PrintWriter scoreOut = new PrintWriter(scorePath, "UTF-8")) {
            dataOut.write(result);
            scoreOut.write(score);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

