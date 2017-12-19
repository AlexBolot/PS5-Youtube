package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Controller
{

    private ArrayList8<Connexion> connexions;
    private ArrayList8<Cache>     caches;
    private ArrayList8<EndPoint>  endPoints;
    private ArrayList8<Video>     videos;
    private DataCenter            dataCenter;

    public Controller (ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints, ArrayList8<Video> videos,
                       DataCenter dataCenter)
    {
        this.connexions = connexions;
        this.caches = caches;
        this.endPoints = endPoints;
        this.videos = videos;
        this.dataCenter = dataCenter;
    }

    public ArrayList8<Connexion> getConnexions ()
    {
        return connexions;
    }

    public void setConnexions (ArrayList8<Connexion> connexions)
    {
        this.connexions = connexions;
    }

    public ArrayList8<Cache> getCaches ()
    {
        return caches;
    }

    public void setCaches (ArrayList8<Cache> caches)
    {
        this.caches = caches;
    }

    public ArrayList8<EndPoint> getEndPoints ()
    {
        return endPoints;
    }

    public void setEndPoints (ArrayList8<EndPoint> endPoints)
    {
        this.endPoints = endPoints;
    }

    public ArrayList8<Video> getVideos ()
    {
        return videos;
    }

    public void setVideos (ArrayList8<Video> videos)
    {
        this.videos = videos;
    }

    public double scoring ()
    {
        int temp = 0;
        int temp2 = 0;
        double score;
        HashMap<Integer, Integer> bestTimes = new HashMap<>();

        for (EndPoint endPoint : endPoints)
        {
            for (Connexion connexion : connexions)
            {
                for (Query query : endPoint.getQueries())
                {
                    if (endPoint.getId() == connexion.getIdEndPoint())
                    {
                        if (caches.get(connexion.getIdCache()).getVideos().contains(query.getVideo()))
                        {
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

    public void generateOutput (String path, String strategyName)
    {
        System.out.println("Number of EndPoints : " + endPoints.size());

        endPoints.forEach(ep -> System.out.printf("EndPoint %d have %d queries%n", ep.getId(), ep.getQueries().size()));

        System.out.println("Number of Video : " + videos.size());

        videos.forEach(video -> System.out.printf("Video %d of %d size%n", video.getId(), video.getSize()));

        System.out.println("Number of Cache : " + caches.size());

        caches.forEach(cache -> System.out.printf("Cache %d of %d capacity and %d video%n",
                                                  cache.getId(),
                                                  cache.getSize(),
                                                  cache.getVideos().size()));

        System.out.printf("DataCenter with %d video%n", dataCenter.getVideos().size());

        StringBuilder sb = new StringBuilder();
        int cacheUsed = (int) caches.stream().filter(c -> !c.getVideos().isEmpty()).count();

        sb.append(cacheUsed).append('\n');

        for (int i = 0; i < cacheUsed; i++)
        {
            ArrayList8<Video> videos = caches.get(i).getVideos();

            if (!videos.isEmpty()) sb.append(i);

            videos.forEach(v -> sb.append(' ').append(v.getId()));

            if (!videos.isEmpty()) sb.append('\n');
        }

        try (PrintWriter writer = new PrintWriter(path + "/data.out", "UTF-8"))
        {
            writer.write(sb.toString());
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        System.out.printf("Output of the score.out file : \n%s\nAnd the score for this strategy : %s\n\n%n", sb.toString(), this.scoring());
    }

}

