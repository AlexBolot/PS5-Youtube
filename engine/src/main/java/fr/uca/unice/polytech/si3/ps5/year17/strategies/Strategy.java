package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

public abstract class Strategy
{
    protected ArrayList8<Connexion> connexions;
    protected ArrayList8<Cache>     caches;
    protected ArrayList8<EndPoint>  endPoints;

    public Strategy (ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints)
    {
        this.connexions = connexions;
        this.caches = caches;
        this.endPoints = endPoints;
    }

    public abstract void apply ();

}
