package fr.uca.unice.polytech.si3.ps5.year17.teamB.benchmark;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Main;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    @Benchmark
    @Fork(value = 10, warmups = 2)
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void init() {
        Main.main(new String[]{"5",
                "/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/me_at_the_zoo.in",
                "/home/doom/Desktop/data.out",
                "/home/doom/Desktop/score.out"});
    }

    @Benchmark
    @Fork(value = 10, warmups = 2)
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void parsing() {

    }

    @Benchmark
    public void measureparsing() {
        Parser parser = new Parser();

        parser.parse("/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/kittens.in.txt");
        parser.parse("/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/me_at_the_zoo.in");
        parser.parse("/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/trending_today.in");
        parser.parse("/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/videos_worth_spreading.in");
    }
}
