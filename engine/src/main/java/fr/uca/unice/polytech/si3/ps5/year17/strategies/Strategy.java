package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.Controller;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;
@SuppressWarnings ("unchecked")
public abstract class Strategy
{
    protected ArrayList8<Cache>     caches;
    protected ArrayList8<EndPoint>  endPoints;
    protected ArrayList8<Connexion> connexions;

    /**
     * Constructor for a strategy
     * @param connexions
     * @param caches
     * @param endPoints
     */
    public Strategy (ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints)
    {
        this.caches = caches;
        this.endPoints = endPoints;
        this.connexions = connexions;
    }

    public Strategy (Controller controller)
    {
        this.caches = (ArrayList8<Cache>) controller.getCaches().clone();
        this.endPoints = (ArrayList8<EndPoint>) controller.getEndPoints().clone();
        this.connexions = (ArrayList8<Connexion>) controller.getConnexions().clone();
    }

    /**
     * Applies the strategy
     */
    public abstract void apply ();

}
