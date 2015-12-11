package com.company.algorithms.flow;


import com.company.fuzzy.hierarchy.Interval;
import com.company.fuzzy.hierarchy.TrapezoidalNumber;
import com.company.fuzzy.hierarchy.TriangularNumber;
import com.company.fuzzy.interfaces.implementations.*;

public class PathLauncher {

    public static void main( String[] args ) {

        /*runIntervalExperiment(10000, 100, 0.2, 0.2, 0.2, 1.0);
        runTriangularExperiment(10000, 100, 0.2, 0.2, 0.2, 1.0);
        runTrapezoidalExperiment(10000, 100, 0.2, 0.2, 0.2, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(10000, 100, 0.1, 0.1, 0.1, 1.0);
        runTriangularExperiment(10000, 100, 0.1, 0.1, 0.1, 1.0);
        runTrapezoidalExperiment(10000, 100, 0.1, 0.1, 0.1, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(20000, 100, 0.3, 0.1, 0.1, 1.0);
        runTriangularExperiment(20000, 100, 0.3, 0.1, 0.1, 1.0);
        runTrapezoidalExperiment(20000, 100, 0.3, 0.1, 0.1, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(20000, 100, 0.1, 0.3, 0.1, 1.0);
        runTriangularExperiment(20000, 100, 0.1, 0.3, 0.1, 1.0);
        runTrapezoidalExperiment(20000, 100, 0.1, 0.3, 0.1, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(20000, 100, 0.1, 0.1, 0.3, 1.0);
        runTriangularExperiment(20000, 100, 0.1, 0.1, 0.3, 1.0);
        runTrapezoidalExperiment(20000, 100, 0.1, 0.1, 0.3, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(10000, 100, 0.2, 0.2, 0.2, 0.5);
        runTriangularExperiment(10000, 100, 0.2, 0.2, 0.2, 0.5);
        runTrapezoidalExperiment(10000, 100, 0.2, 0.2, 0.2, 0.5);

        System.out.println("______________________________");

        runIntervalExperiment(10000, 200, 0.2, 0.2, 0.2, 1.0);
        runTriangularExperiment(10000, 200, 0.2, 0.2, 0.2, 1.0);
        runTrapezoidalExperiment(10000, 200, 0.2, 0.2, 0.2, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(10000, 200, 0.1, 0.1, 0.1, 1.0);
        runTriangularExperiment(10000, 200, 0.1, 0.1, 0.1, 1.0);
        runTrapezoidalExperiment(10000, 200, 0.1, 0.1, 0.1, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(20000, 100, 0.5, 0.0, 0.0, 1.0);
        runTriangularExperiment(20000, 100, 0.5, 0.0, 0.0, 1.0);
        runTrapezoidalExperiment(20000, 100, 0.5, 0.0, 0.0, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(20000, 100, 0.0, 0.5, 0.0, 1.0);
        runTriangularExperiment(20000, 100, 0.0, 0.5, 0.0, 1.0);
        runTrapezoidalExperiment(20000, 100, 0.0, 0.5, 0.0, 1.0);

        System.out.println("______________________________");

        runIntervalExperiment(20000, 100, 0.0, 0.0, 0.5, 1.0);
        runTriangularExperiment(20000, 100, 0.0, 0.0, 0.5, 1.0);
        runTrapezoidalExperiment(20000, 100, 0.0, 0.0, 0.5, 1.0);

        System.out.println("______________________________");*/

        runTriangularUniExperiment(1, 100, 0.3, 0.3, 1.0);
        //runTriangularUniExperiment(100000, 100, 0.1, 0.5, 1.0);
        //runTriangularUniExperiment(100000, 100, 0.5, 0.1, 1.0);
        /*runTriangularUniExperiment(10000, 200, 0.3, 0.3, 1.0);
        runTriangularUniExperiment(10000, 200, 0.3, 0.3, 2.0);
        runTriangularUniExperiment(10000, 100, 0.6, 0.0, 1.0);*/

    }

    private static void runIntervalExperiment(int experiments, int nodes, double triPoss, double trapPoss, double intPoss, double timeLimit) {
        double intervalFreeEntropy = 0.;
        double intervalChainEntropy1 = 0.;
        double intervalChainEntropy2 = 0.;

        for (int i = 0; i < experiments; ++i) {

            GraphGenerator.getInstance().generateRandomGraph(nodes, triPoss, trapPoss, intPoss);
            GraphGenerator.getInstance().init(
                    IntTriRandomConverter.getInstance(),
                    TriTrapRandomConverter.getInstance(),
                    TrapIntRandomConverter.getInstance());

            GraphGenerator.getInstance().setFreeMode();

            intervalFreeEntropy += new DijkstraMinPath<Interval, IntPossOps, IntLogEntropy>(IntPossOps.getInstance(), IntLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().intervalGraph, 0, nodes - 1, timeLimit);

            GraphGenerator.getInstance().setChainMode(new int[]{2, 2, 0});

            intervalChainEntropy1 += new DijkstraMinPath<Interval, IntPossOps, IntLogEntropy>(IntPossOps.getInstance(), IntLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().intervalGraph, 0, nodes - 1, timeLimit);

            GraphGenerator.getInstance().setChainMode(new int[] {0, 1, 1});

            intervalChainEntropy2 += new DijkstraMinPath<Interval, IntPossOps, IntLogEntropy>(IntPossOps.getInstance(), IntLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().intervalGraph, 0, nodes - 1, timeLimit);

        }

        System.out.println(String.format("\nInterval experiment finished. Iterations: %d. Nodes: %d. Possibilities: %1.2f, %1.2f, %1.2f. Time limit: %1.2f." +
                "\nFree: %f. Chain 1: %f. Chain 2: %f"
                , experiments , nodes, triPoss, trapPoss, intPoss, timeLimit
                , intervalFreeEntropy / (experiments + 1)
                , intervalChainEntropy1 / (experiments + 1)
                , intervalChainEntropy2 / (experiments + 1)));
    }

    private static void runTriangularExperiment(int experiments, int nodes, double triPoss, double trapPoss, double intPoss, double timeLimit) {
        double triangularFreeEntropy = 0.;
        double triangularChainEntropy1 = 0.;
        double triangularChainEntropy2 = 0.;

        for (int i = 0; i < experiments; ++i) {

            GraphGenerator.getInstance().generateRandomGraph(nodes, triPoss, trapPoss, intPoss);
            GraphGenerator.getInstance().init(
                    IntTriRandomConverter.getInstance(),
                    TriTrapRandomConverter.getInstance(),
                    TrapIntRandomConverter.getInstance());

            GraphGenerator.getInstance().setFreeMode();

            triangularFreeEntropy += new DijkstraMinPath<TriangularNumber, TriAveragePossOps, TriLogEntropy>(TriAveragePossOps.getInstance(), TriLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().triangularGraph, 0, nodes - 1, timeLimit);

            GraphGenerator.getInstance().setChainMode(new int[]{1, 0, 1});

            triangularChainEntropy1 += new DijkstraMinPath<TriangularNumber, TriAveragePossOps, TriLogEntropy>(TriAveragePossOps.getInstance(), TriLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().triangularGraph, 0, nodes - 1, timeLimit);

            GraphGenerator.getInstance().setChainMode(new int[] {0, 2, 2});

            triangularChainEntropy2 += new DijkstraMinPath<TriangularNumber, TriAveragePossOps, TriLogEntropy>(TriAveragePossOps.getInstance(), TriLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().triangularGraph, 0, nodes - 1, timeLimit);

        }

        System.out.println(String.format("\nTriangular experiment finished. Iterations: %d. Nodes: %d. Possibilities: %1.2f, %1.2f, %1.2f. Time limit: %1.2f." +
                "\nFree: %f. Chain 1: %f. Chain 2: %f"
                , experiments , nodes, triPoss, trapPoss, intPoss, timeLimit
                , triangularFreeEntropy / (experiments + 1)
                , triangularChainEntropy1 / (experiments + 1)
                , triangularChainEntropy2 / (experiments + 1)));
    }

    private static void runTrapezoidalExperiment(int experiments, int nodes, double triPoss, double trapPoss, double intPoss, double timeLimit) {
        double trapezoidalFreeEntropy = 0.;
        double trapezoidalChainEntropy1 = 0.;
        double trapezoidalChainEntropy2 = 0.;

        for (int i = 0; i < experiments; ++i) {

            GraphGenerator.getInstance().generateRandomGraph(nodes, triPoss, trapPoss, intPoss);
            GraphGenerator.getInstance().init(
                    IntTriRandomConverter.getInstance(),
                    TriTrapRandomConverter.getInstance(),
                    TrapIntRandomConverter.getInstance());

            GraphGenerator.getInstance().setFreeMode();

            trapezoidalFreeEntropy += new DijkstraMinPath<TrapezoidalNumber, TrapAveragePossOps, TrapLogEntropy>(TrapAveragePossOps.getInstance(), TrapLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().trapezoidalGraph, 0, nodes - 1, timeLimit);

            GraphGenerator.getInstance().setChainMode(new int[]{1, 1, 0});

            trapezoidalChainEntropy1 += new DijkstraMinPath<TrapezoidalNumber, TrapAveragePossOps, TrapLogEntropy>(TrapAveragePossOps.getInstance(), TrapLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().trapezoidalGraph, 0, nodes - 1, timeLimit);

            GraphGenerator.getInstance().setChainMode(new int[]{2, 0, 2});

            trapezoidalChainEntropy2 += new DijkstraMinPath<TrapezoidalNumber, TrapAveragePossOps, TrapLogEntropy>(TrapAveragePossOps.getInstance(), TrapLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().trapezoidalGraph, 0, nodes - 1, timeLimit);

        }

        System.out.println(String.format("\nTrapezoidal experiment finished. Iterations: %d. Nodes: %d. Possibilities: %1.2f, %1.2f, %1.2f. Time limit: %1.2f." +
                "\nFree: %f. Chain 1: %f. Chain 2: %f"
                , experiments , nodes, triPoss, trapPoss, intPoss, timeLimit
                , trapezoidalFreeEntropy / (experiments + 1)
                , trapezoidalChainEntropy1 / (experiments + 1)
                , trapezoidalChainEntropy2 / (experiments + 1)));
    }

    private static void runTriangularUniExperiment(int experiments, int nodes, double triPoss, double intPoss, double timeLimit) {
        double triangularBasicEntropy = 0.0;
        double triangularUniformEntropy = 0.0;

        for (int i = 0; i < experiments; ++i) {
            GraphGenerator.getInstance().generateRandomGraph(nodes, triPoss, 0, intPoss);
            GraphGenerator.getInstance().init(
                    IntTriBasicConverter.getInstance(),
                    null,
                    null);
            GraphGenerator.getInstance().setFreeMode();

            triangularBasicEntropy += new DijkstraMinPath<TriangularNumber, TriMaxAveragePossOps, TriLogEntropy>(TriMaxAveragePossOps.getInstance(), TriLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().triangularGraph, 0, nodes - 1, timeLimit);

            GraphGenerator.getInstance().init(
                    IntTriUniConverter.getInstance(),
                    null,
                    null);
            GraphGenerator.getInstance().setFreeMode();

            triangularUniformEntropy += new DijkstraMinPath<TriangularNumber, TriMaxAveragePossOps, TriLogEntropy>(TriMaxAveragePossOps.getInstance(), TriLogEntropy.getInstance())
                    .getSTEntropy(GraphGenerator.getInstance().triangularGraph, 0, nodes - 1, timeLimit);
        }

        System.out.println(String.format("\nTriangular uniform experiment finished. Nodes: %d. Possibilities: %1.2f, %1.2f. Time limit: %1.2f." +
                "\nBasic: %f. Uniform: %f."
                , nodes, intPoss, triPoss, timeLimit, triangularBasicEntropy / experiments, triangularUniformEntropy / experiments));
    }

}
