package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

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

    public int Scoring(){
        int temp = 0;
        int temp2 = 0;
        int score;

        for(EndPoint e : endPoints){
            for (Video v : e.getWantedVideos().keySet()){
                for(Cache c : caches){
                    if(c.getVideos().contains(v)) {
                        for (Connexion co : connexions) {
                            if (co.getIdCache() == c.getId() && co.getIdEndPoint() == e.getId()) {
                                temp += Integer.parseInt(e.getWantedVideos().entrySet().toString()) * (e.getDataCenterLatency() - co.getLatency());
                                temp2 += Integer.parseInt(e.getWantedVideos().entrySet().toString());
                            }else{continue;}
                        }
                    }else {continue;}
                }
            }
        }
        score = temp / temp2;

        return score;
    }
}
