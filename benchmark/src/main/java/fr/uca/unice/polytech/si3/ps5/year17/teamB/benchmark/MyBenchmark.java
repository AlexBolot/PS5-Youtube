package fr.uca.unice.polytech.si3.ps5.year17.teamB.benchmark;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Controller;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Main;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.CacheIfQueryStrategy;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.FirstInStrategy;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.RandomStrategy;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.Strategy;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(value = Scope.Benchmark)
public class MyBenchmark {

    private DataBundle data;
    private Controller controller;
    private String[] args;
    private Parser parser;
    private String kittensFile;
    private Strategy random;
    private Strategy firstIn;
    private Strategy cacheIfQuery;


    @Setup(Level.Trial)
    public void setup() {
        this.args = new String[]{"6",
                "/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/me_at_the_zoo.in",
                "/home/doom/Desktop/data.out",
                "/home/doom/Desktop/score.out"};
        this.kittensFile = "/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/kittens.in.txt";
        this.parser = new Parser();
        this.controller = new Controller(null);
        parser.parse("/home/doom/Documents/Git/Polytech/Collet_wa/engine/src/main/resources/me_at_the_zoo.in");
        this.data = parser.getData();
        this.random = new RandomStrategy(data);
        this.firstIn = new FirstInStrategy(data);
        this.cacheIfQuery = new CacheIfQueryStrategy(data);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @Threads(4)
    public void init() {
        Main.main(args);
    }

    @Benchmark
    @Threads(4)
    @Fork(value = 10, warmups = 2)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @BenchmarkMode(Mode.Throughput)
    public void measureScoring() {
        controller.scoring(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 10, warmups = 2)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureRandomStrategy() {
        random.apply();
        random = new RandomStrategy(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 10, warmups = 2)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureFirstInStrategy() {
        firstIn.apply();
        firstIn = new FirstInStrategy(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 10, warmups = 2)
    @Warmup(iterations = 5)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureCacheIfQueryStrategy() {
        cacheIfQuery.apply();
        cacheIfQuery = new CacheIfQueryStrategy(data);
    }

    @Benchmark
    @Fork(value = 5, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureParsingKittens() {
        parser.parse(kittensFile);
    }
}