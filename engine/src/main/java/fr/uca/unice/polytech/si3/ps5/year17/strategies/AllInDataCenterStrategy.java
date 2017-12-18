package fr.uca.unice.polytech.si3.ps5.year17.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.Connexion;
import fr.uca.unice.polytech.si3.ps5.year17.EndPoint;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.util.List;

public class AllInDataCenterStrategy extends Strategy
{

    public AllInDataCenterStrategy (List<Connexion> connexions, List<Cache> caches, List<EndPoint> endPoints)
    {
        super(connexions, caches, endPoints);
    }

    public void apply ()
    {
        // DO NOTHING
    }
}
