package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.strategies.Strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Controller {

    private Strategy strategy;

    public Controller(Strategy strategy) {
        this.strategy = strategy;
    }

    public double scoring() {
        long temp = 0;
        long temp2 = 0;
        double score = 0;
        HashMap<Integer, Long> bestTimes = new HashMap<>();

        for (EndPoint endPoint : strategy.getData().getEndPoints()) {
            for (Connection connection : strategy.getData().getConnections()) {
                for (Query query : endPoint.getQueries()) {
                    if (endPoint.getId() == connection.getIdEndPoint()) {
                        if (strategy.getData().getCaches().get(connection.getIdCache()).getVideos().contains(query.getVideo())) {
                            int videoID = query.getVideo().getId();
                            long nbRequest = query.getNumberOfRequests();
                            long dataCenterLatency = endPoint.getDataCenterLatency();
                            long connexionLatency = connection.getLatency();

                            long totalGain = (nbRequest * (dataCenterLatency - connexionLatency));

                            if (!bestTimes.containsKey(videoID)) bestTimes.put(videoID, totalGain);
                            else if (bestTimes.get(videoID) < totalGain) bestTimes.put(videoID, totalGain);
                        }
                    }
                }
            }

            temp2 += endPoint.getQueries().stream().mapToInt(Query::getNumberOfRequests).sum();
        }

        temp = bestTimes.keySet().stream().mapToLong(bestTimes::get).sum();

        score = (double) temp / temp2;

        return Math.floor(score * 1000);
    }

    public void generateOutput(String path, String inputFileName) {

        strategy.apply();

        String result = strategy.toString();
        String score = scoring() + "";

        System.out.println("Strategy : " + strategy.getClass().getSimpleName() + "\n");
        System.out.println("Data Output : \n\n" + result + "\n");
        System.out.println("Score : " + score + "\n");

        String outputDir = path + inputFileName;

        new File(outputDir).mkdirs();

        try (PrintWriter dataOut    = new PrintWriter(outputDir + "/data.out", "UTF-8");
             PrintWriter scoreOut   = new PrintWriter(outputDir + "/score.out", "UTF-8")) {
            dataOut.write(result);
            scoreOut.write(score);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

