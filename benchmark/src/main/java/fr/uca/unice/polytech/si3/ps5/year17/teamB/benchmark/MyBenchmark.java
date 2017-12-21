package fr.uca.unice.polytech.si3.ps5.year17.teamB.benchmark;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Main;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {


    @Benchmark
    @Fork(value = 2, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Threads(4)
    public void init() {
        Main.main(new String[]{"6",
                "/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/me_at_the_zoo.in",
                "/home/doom/Desktop/data.out",
                "/home/doom/Desktop/score.out"});
    }


    @Benchmark
    @Fork(value = 10, warmups = 2)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureParsingKittens() {
        Parser parser = new Parser();
        parser.parse("/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/kittens.in.txt");
    }
}
