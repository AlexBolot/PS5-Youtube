package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Controller;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class FirstInStrategyTest {
    @Test
    void apply() {

        Parser parser = new Parser(this.getClass().getResource("/me_at_the_zoo.in").getPath());

        Strategy strategy = new FirstInStrategy(parser.getData());

        strategy.apply();

        assertEquals(parser.getData(), strategy.getData());

        Controller controller = new Controller(strategy);

        assertEquals(controller.scoring(strategy.getData()), 112167.0, 0.1);

    }

}