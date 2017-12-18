package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.HashMap;
import java.util.Map;

public class Controller
{
    private ArrayList8<Connexion> connexions;
    private ArrayList8<Cache>     caches;
    private ArrayList8<EndPoint>  endPoints;

    public Controller (ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints)
    {
        this.connexions = connexions;
        this.caches = caches;
        this.endPoints = endPoints;
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

    public int scoring(){
        int temp = 0;
        int temp2 = 0;
        int score;

        for(EndPoint e : endPoints){
            for (Connexion c : connexions){
                for (Video v : e.getWantedVideos().keySet()){
                    if (e.getId() == c.getIdEndPoint()){
                        if (caches.get(c.getIdCache()).getVideos().contains(v)){
                            System.out.println(v.getId());
                            System.out.println(e.getWantedVideos().get(v) + " * (" + e.getDataCenterLatency() + " - " + c.getLatency() + ")");
                            temp += e.getWantedVideos().get(v) * (e.getDataCenterLatency() - c.getLatency());
                        }
                    }
                }
            }
            for (int i : e.getWantedVideos().values()){
                temp2+= i;
            }
        }
        System.out.println(temp2);
        score = temp / temp2;

        return score * 1000;
    }
}
