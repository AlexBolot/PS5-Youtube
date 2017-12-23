package fr.uca.unice.polytech.si3.ps5.year17.teamB.benchmark;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Controller;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.*;
import org.openjdk.jmh.annotations.*;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@State(value = Scope.Benchmark)
public class MyBenchmark {

    private DataBundle data;
    private Controller controller;
    private Parser parser;
    private InputStream kittensFile;
    private Strategy random;
    private Strategy firstIn;
    private Strategy cacheIfQuery;
    private Strategy weird;
    private Strategy bestForEachCache;
    private Strategy average;
    private Strategy probategy;
    private Strategy lighestInCache;


    @Setup(Level.Trial)
    public void setup() {
        this.kittensFile = this.getClass().getResourceAsStream("/kittens.in.txt");
        this.parser = new Parser();
        this.controller = new Controller(null);
        parser.parse(this.getClass().getResourceAsStream("/me_at_the_zoo.in"));
        this.data = parser.getData();
        this.random = new RandomStrategy(data);
        this.firstIn = new FirstInStrategy(data);
        this.cacheIfQuery = new CacheIfQueryStrategy(data);
        this.average = new AverageStrategy(data);
        this.lighestInCache = new LightestsInCache(data);
        this.weird = new WeirdStrategy(data);
        this.probategy = new ProbaTegy(data);
        this.bestForEachCache = new BestForEachCacheStrategy(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureScoring() {
        controller.scoring(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureRandomStrategy() {
        random.apply();
        random = new RandomStrategy(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
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
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureWeirdStrategy() {
        weird.apply();
        weird = new WeirdStrategy(data);
    }

    @Benchmark
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureProbaTegy() {
        probategy.apply();
        probategy = new ProbaTegy(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureBestBeforeCacheStrategy() {
        bestForEachCache.apply();
        bestForEachCache = new BestForEachCacheStrategy(data);
    }

    @Benchmark
    @Threads(4)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureAverageStrategy() {
        average.apply();
        average = new AverageStrategy(data);
    }

    @Benchmark
    @Threads(1)
    @BenchmarkMode(Mode.Throughput)
    @Fork(value = 2, warmups = 2)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureLighestInCacheStrategy() {
        lighestInCache.apply();
        lighestInCache = new LightestsInCache(data);
    }

    @Benchmark
    @Fork(value = 2, warmups = 1)
    @BenchmarkMode(Mode.Throughput)
    @Warmup(iterations = 2)
    @Measurement(iterations = 2)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void measureParsingKittens() {
        parser.parse(kittensFile);
    }
}