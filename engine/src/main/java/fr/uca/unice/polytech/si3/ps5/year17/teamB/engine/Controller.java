package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.Strategy;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Controller
{

    private Strategy strategy;

    public Controller (Strategy strategy)
    {
        this.strategy = strategy;
    }

    /**
     @param data all the data, connections, endpoints, caches, videos and data center
     @return the calculate score (number of request * (latency with the data center - latency with the cache))
     */
    public double scoring (DataBundle data)
    {
        double allScore = 0;
        double nbAllRequests = 0;
        double finalScore = 0;

        for (EndPoint endPoint : data.getEndPoints())
        {
            HashMap<Integer, Double> bestTimes = new HashMap<>();
            for (Connection connection : data.getConnections())
            {
                if (endPoint.getId() == connection.getIdEndPoint())
                {
                    for (Query query : endPoint.getQueries())
                    {
                        if (data.getCaches().get(connection.getIdCache()).getVideos().contains(query.getVideo()))
                        {
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

            nbAllRequests += endPoint.getQueries().stream().mapToInt(Query::getNumberOfRequests).sum();
            allScore += bestTimes.keySet().stream().mapToDouble(bestTimes::get).sum();
        }

        finalScore = allScore / nbAllRequests;

        return Math.floor(finalScore * 1000);
    }

    /**
     Generates both data.out and score.out files at the specified paths (as params)
     Data.out contains, the number of caches used, the list of the caches and for each cache, the videos inside them.

     <hr>
     <h2>Generates both data.out and score.out files at the specified paths (as params)</h2>
     <h3>Data.out contains, the number of caches used, the list of the caches and for each cache, the videos inside them.</h3>
     <h3>Score.out contains the score of the strategy</h3>
     <hr>

     @param dataPath  Path to generate the data.out file
     @param scorePath Path to generate the score.out file
     */
    public void generateOutput (String dataPath, String scorePath)
    {
        strategy.apply();

        String result = strategy.toString();
        String score = scoring(strategy.getData()) + "";

        try (PrintWriter dataOut = new PrintWriter(dataPath, "UTF-8"); PrintWriter scoreOut = new PrintWriter(scorePath, "UTF-8"))
        {
            dataOut.write(result);
            scoreOut.write(score);
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

    }

}

