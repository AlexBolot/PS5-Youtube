package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.strategies.Strategy;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Controller {

    private ArrayList8<Strategy> strategies;

    public Controller() {
        this.strategies = new ArrayList8<>();
    }

    public Controller(ArrayList8<Strategy> strategies) {
        this.strategies = strategies;
    }

    public boolean addStrategy(Strategy strategy) {
        return this.strategies.add(strategy);
    }

    public boolean removeStrategy(Strategy strategy) {
        return this.strategies.remove(strategy);
    }

    public double scoring(Strategy strategy) {
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

        System.out.println(temp + " / " + temp2);

        score = (double) temp / temp2;

        return Math.floor(score * 1000);
    }

    public void generateOutput(String path, String inputFileName) {

        StringBuilder dataString = new StringBuilder();
        StringBuilder scoreString = new StringBuilder();

        for (Strategy strategy : strategies) {

            strategy.apply();

            String result = strategy.toString();
            String score = scoring(strategy) + "";

            System.out.println("Strategy : " + strategy.getClass().getSimpleName() + "\n");
            System.out.println("Data Output : \n\n" + result + "\n");
            System.out.println("Score : " + score + "\n");

            dataString.append(result).append('\n');
            scoreString.append(score).append(' ');
        }

        String outputDir = path + inputFileName;

        new File(outputDir).mkdirs();

        try (PrintWriter dataOut    = new PrintWriter(outputDir + "/data.out", "UTF-8");
             PrintWriter scoreOut   = new PrintWriter(outputDir + "/score.out", "UTF-8")) {
            dataOut.write(dataString.toString());
            scoreOut.write(scoreString.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}

