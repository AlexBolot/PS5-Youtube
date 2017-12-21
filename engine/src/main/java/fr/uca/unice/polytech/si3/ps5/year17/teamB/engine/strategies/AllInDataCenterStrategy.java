package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;

/**
 * All videos are sent to endpoints directly from the datacenter
 */
public class AllInDataCenterStrategy extends Strategy {

    public AllInDataCenterStrategy(DataBundle data) {
        super(data, 0);
    }

    public void apply() {
        // DO NOTHING
    }
}
