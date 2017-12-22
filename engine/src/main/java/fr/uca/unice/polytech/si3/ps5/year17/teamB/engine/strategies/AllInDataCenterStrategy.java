package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;

/**
 <hr>
 <h2>All in DataCenter Strategy :</h2>
 <h3>It keeps all the videos in the DataCenter and puts none in the caches. Therefore its score is of zero</h3>
 <hr>
 */
public class AllInDataCenterStrategy extends Strategy
{
    /**
     <hr>
     <h2>Default Constructor of this Strategy<br>
     <br>
     See {@link Strategy#Strategy(DataBundle, int)}
     </h2>
     <hr>

     @param data The DataBundle to apply this Strategy on.
     */
    public AllInDataCenterStrategy (DataBundle data)
    {
        super(data, 0);
    }

    /**
     <hr>
     <h2>Implementation of the apply method<br>
     Does nothing for this strategy
     </h2>
     <hr>
     */
    public void apply ()
    {
        // DO NOTHING :D
    }
}
