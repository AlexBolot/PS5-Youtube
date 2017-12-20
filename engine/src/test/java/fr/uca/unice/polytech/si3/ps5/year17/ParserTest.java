package fr.uca.unice.polytech.si3.ps5.year17;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    @Test
    void parseTest() {
        Parser parser = new Parser();
        String path = Main.class.getResource("/me_at_the_zoo.in").getPath();
        parser.parse(path);
    }

}