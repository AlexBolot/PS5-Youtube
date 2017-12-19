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
        double score = 0;
        HashMap<Integer, Integer> bestTimes = new HashMap<>();

        for (EndPoint e : endPoints)
        {
            for (Connexion c : connexions)
            {
                for (Query q : e.getQueries())
                {
                    if (e.getId() == c.getIdEndPoint())
                    {
                        if (caches.get(c.getIdCache()).getVideos().contains(q.getVideo()))
                        {
                            int tot = q.getNumberOfRequests() * (e.getDataCenterLatency() - c.getLatency());
                            if (!bestTimes.containsKey(q.getVideo().getId())) bestTimes.put(q.getVideo().getId(), tot);
                            if (bestTimes.containsKey(q.getVideo().getId()) && bestTimes.get(q.getVideo().getId()) < tot)
                                bestTimes.put(q.getVideo().getId(), tot);
                        }
                    }
                }
            }
            for (Query q : e.getQueries())
            {
                temp2 += q.getNumberOfRequests();
            }
        }

        for (Integer key : bestTimes.keySet())
        {
            temp += bestTimes.get(key);
        }

        /*System.out.println(temp2);
        System.out.println(temp);*/
        score = (double)((temp / temp2)*1000);

        return score;
    }

    public void generateOutput (String path)
    {

        System.out.println("Number of EndPoints : " + this.endPoints.size());
        for (EndPoint ep : this.endPoints)
        {
            System.out.println("EndPoint " + ep.getId() + " have " + ep.getQueries().size() + " queries");
        }
        System.out.println("Number of Video : " + this.videos.size());
        for (Video video : this.videos)
        {
            System.out.println("Video " + video.getId() + " of " + video.getSize() + " size");
        }
        System.out.println("Number of Cache : " + this.caches.size());
        for (Cache cache : this.caches)
        {
            System.out.println("Cache " + cache.getId() + " of " + cache.getSize() + " capacity and " + cache.getVideos()
                                                                                                             .size() + " video");
        }
        System.out.println("DataCenter with " + dataCenter.getVideos().size() + " video");

        StringBuilder sb = new StringBuilder();
        int cacheUsed = 0;
        for (Cache c : this.caches)
        {
            if (!c.getVideos().isEmpty()) cacheUsed++;
        }
        sb.append(cacheUsed).append('\n');
        for (int i = 0; i < cacheUsed; i++)
        {
            if (!this.caches.get(i).getVideos().isEmpty()) sb.append(i);
            for (Video v : this.caches.get(i).getVideos())
            {
                sb.append(' ').append(v.getId());
            }
            if (!this.caches.get(i).getVideos().isEmpty()) sb.append('\n');
        }
        try (PrintWriter writer = new PrintWriter(path + "/score.out", "UTF-8"))
        {
            writer.write(sb.toString());
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        System.out.println("Output of the score.out file : \n" + sb.toString() + "\nAnd the score for this strategy : " + this.scoring() + "\n\n");
    }

}

