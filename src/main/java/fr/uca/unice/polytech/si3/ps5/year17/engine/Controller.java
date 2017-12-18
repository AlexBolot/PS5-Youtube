package fr.uca.unice.polytech.si3.ps5.year17.engine;

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
}
