/**
 *
 */

package fr.uca.unice.polytech.si3.ps5.year17.teamB.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkRunner {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }

}
