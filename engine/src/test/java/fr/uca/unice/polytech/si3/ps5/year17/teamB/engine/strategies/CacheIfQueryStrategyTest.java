package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import org.junit.Test;

public class CacheIfQueryStrategyTest
{
    @Test
    public void apply () throws Exception
    {
        Parser parser = new Parser(this.getClass().getResource("/me_at_the_zoo.in").getPath());

        Strategy strategy = new CacheIfQueryStrategy(parser.getData());
    }
}