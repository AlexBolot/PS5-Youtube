package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.ArrayList;
import java.util.List;

public abstract class Strategy
{
    protected List<Connexion> connexions;
    protected List<Cache>     caches;
    protected List<EndPoint> endPoints;

    public Strategy (List<Connexion> connexions, List<Cache> caches, List<EndPoint> endPoints)
    {
        this.connexions = connexions;
        this.caches = caches;
        this.endPoints = endPoints;
    }

    public abstract void apply ();

}
