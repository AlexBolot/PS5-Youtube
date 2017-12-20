package fr.uca.unice.polytech.si3.ps5.year17;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    @Benchmark
    @Fork(value = 10, warmups = 2)
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void init(){
        Main.main(new String[] {"5",
                "/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/me_at_the_zoo.in",
                "/home/doom/Desktop/data.out",
                "/home/doom/Desktop/score.out"});
    }
}
