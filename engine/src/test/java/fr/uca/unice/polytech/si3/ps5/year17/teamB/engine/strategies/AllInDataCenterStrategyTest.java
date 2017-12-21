package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AllInDataCenterStrategyTest
{
    @Test
    public void apply ()
    {
        Parser parser = new Parser(this.getClass().getResource("/me_at_the_zoo.in").getPath());

        Strategy strategy = new AllInDataCenterStrategy(parser.getData());

        strategy.apply();

        assertEquals(parser.getData(), strategy.getData());
    }
}