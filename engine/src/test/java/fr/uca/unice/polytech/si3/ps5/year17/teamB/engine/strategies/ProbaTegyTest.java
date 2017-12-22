package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Controller;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ProbaTegyTest {
    @Test
    void apply() {

        Parser parser = new Parser(this.getClass().getResource("/me_at_the_zoo.in").getPath());

        Strategy strategy = new ProbaTegy(parser.getData());

        strategy.apply();

        Controller controller = new Controller(strategy);

        System.out.println(controller.scoring(strategy.getData()));

        assertEquals(controller.scoring(strategy.getData()), 144826.0, 1*10^-1);


    }

}