package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.strategies.Strategy;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import javax.xml.bind.DataBindingException;
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
        int temp = 0;
        int temp2 = 0;
        double score;
        HashMap<Integer, Integer> bestTimes = new HashMap<>();

        for (EndPoint endPoint : strategy.getData().getEndPoints()) {
            for (Connexion connexion : strategy.getData().getConnexions()) {
                for (Query query : endPoint.getQueries()) {
                    if (endPoint.getId() == connexion.getIdEndPoint()) {
                        if (strategy.getData().getCaches().get(connexion.getIdCache()).getVideos().contains(query.getVideo())) {
                            int videoID = query.getVideo().getId();
                            int nbRequest = query.getNumberOfRequests();
                            int dataCenterLatency = endPoint.getDataCenterLatency();
                            int connexionLatency = connexion.getLatency();

                            int totalGain = nbRequest * (dataCenterLatency - connexionLatency);

                            if (!bestTimes.containsKey(videoID)) bestTimes.put(videoID, totalGain);
                            else if (bestTimes.get(videoID) < totalGain) bestTimes.put(videoID, totalGain);
                        }
                    }
                }
            }

            temp2 += endPoint.getQueries().stream().mapToInt(Query::getNumberOfRequests).sum();
        }

        temp = bestTimes.keySet().stream().mapToInt(bestTimes::get).sum();

        score = (double) temp / temp2;

        return Math.floor(score * 1000);
    }

    public void generateOutput(String path) {

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

        try (PrintWriter dataOut = new PrintWriter(path + "/data.out", "UTF-8");
             PrintWriter scoreOut = new PrintWriter(path + "/score.out", "UTF-8")) {
            dataOut.write(dataString.toString());
            scoreOut.write(scoreString.toString());
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        System.out.println("Number of EndPoints : " + endPoints.size());
//
//        endPoints.forEach(ep -> System.out.printf("EndPoint %d have %d queries%n", ep.getId(), ep.getQueries().size()));
//
//        System.out.println("Number of Video : " + videos.size());
//
//        videos.forEach(video -> System.out.printf("Video %d of %d size%n", video.getId(), video.getSize()));
//
//        System.out.println("Number of Cache : " + caches.size());
//
//        caches.forEach(cache -> System.out.printf("Cache %d of %d capacity and %d video%n",
//                cache.getId(),
//                cache.getSize(),
//                cache.getVideos().size()));
//
//        System.out.printf("DataCenter with %d video%n", dataCenter.getVideos().size());
//
//        StringBuilder sb = new StringBuilder();
//        int cacheUsed = (int) caches.stream().filter(c -> !c.getVideos().isEmpty()).count();
//
//        sb.append(cacheUsed).append('\n');
//
//        for (int i = 0; i < cacheUsed; i++) {
//            ArrayList8<Video> videos = caches.get(i).getVideos();
//
//            if (!videos.isEmpty()) sb.append(i);
//
//            videos.forEach(v -> sb.append(' ').append(v.getId()));
//
//            if (!videos.isEmpty()) sb.append('\n');
//        }
//
//        try (PrintWriter writer = new PrintWriter(path + "/data.out", "UTF-8")) {
//            writer.write(sb.toString());
//        } catch (FileNotFoundException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        System.out.printf("Output of the score.out file : \n%s\nAnd the score for this strategy : %s\n\n%n", sb.toString(), this.scoring());
    }

}

