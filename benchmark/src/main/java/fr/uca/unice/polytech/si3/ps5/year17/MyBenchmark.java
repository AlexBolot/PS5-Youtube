package fr.uca.unice.polytech.si3.ps5.year17;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    @Benchmark
    @Fork(value = 10, warmups = 2)
    @BenchmarkMode(Mode.SampleTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public void init(){
        Main.main(null);
    }
}
