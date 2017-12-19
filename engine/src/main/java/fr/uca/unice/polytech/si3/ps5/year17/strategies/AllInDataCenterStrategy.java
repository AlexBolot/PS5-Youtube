package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.Controller;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

/**
 * All videos are sent to endpoints directly from the datacenter
 */
public class AllInDataCenterStrategy extends Strategy
{
    public AllInDataCenterStrategy (ArrayList8<Connexion> connexions, ArrayList8<Cache> caches, ArrayList8<EndPoint> endPoints)
    {
        super(connexions, caches, endPoints);
    }

    public AllInDataCenterStrategy (Controller controller)
    {
        super(controller);
    }

    public void apply ()
    {
        // DO NOTHING
    }
}
