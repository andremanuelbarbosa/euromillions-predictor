package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.AlgorithmComparator;
import com.andremanuelbarbosa.euromillions.predictor.algorithms.IgnoreAlgorithm;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.reflections.Reflections;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;

public class TimeMachine {

    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static final DecimalFormat DECIMAL_FORMAT_AVERAGE_POINTS = new DecimalFormat("0.00");

    private final ConcurrentHashMap<Class<? extends Algorithm>, Integer> algorithmsPointsSum = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<? extends Algorithm>, Integer> algorithmsModePoints = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<? extends Algorithm>, Integer> algorithmsMaximumPoints = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<? extends Algorithm>, Double> algorithmsAveragePoints = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<Class<? extends Algorithm>, Integer> algorithmsWins = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<? extends Algorithm>, ConcurrentHashMap<Integer, Integer>> algorithmsPoints = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<? extends Algorithm>, ConcurrentHashMap<Integer, Integer>> algorithmsStarsDistributedFreq = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Class<? extends Algorithm>, ConcurrentHashMap<Integer, Integer>> algorithmsNumbersDistributedFreq = new ConcurrentHashMap<>();

    private final List<Snapshot> snapshots = new LinkedList<>();
    private final List<Class<? extends Algorithm>> algorithmClasses = new LinkedList<>();

    private final ExecutorService executorServiceSnapshots = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);
    private final ExecutorService executorServiceAlgorithms = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

    private final List<? extends Draw> draws;
    private final int minimumDrawsIndex;

    private long executionTime;

    public TimeMachine(List<? extends Draw> draws) {

        this(draws, 100);
    }

    public TimeMachine(List<? extends Draw> draws, int minimumDrawsIndex) {

        this.draws = draws;
        this.minimumDrawsIndex = minimumDrawsIndex;

        final long startTime = System.currentTimeMillis();

        loadAlgorithmClasses();
        loadSnapshots();

        executeAlgorithms();

        executionTime = System.currentTimeMillis() - startTime;
    }

    private void loadAlgorithmClasses() {

        final Reflections reflections = new Reflections(Algorithm.class.getPackage().getName());

        algorithmClasses.addAll(reflections.getSubTypesOf(Algorithm.class).stream().filter(algorithmClass ->
            Lists.newArrayList(algorithmClass.getAnnotations()).stream().noneMatch(annotation -> annotation instanceof IgnoreAlgorithm)).collect(Collectors.toList()));

        algorithmClasses.sort(new AlgorithmComparator());
    }

    private void loadSnapshots() {

        for (int i = minimumDrawsIndex; i < (draws.size() - 1); i++) {

            final Snapshot snapshot = new Snapshot(draws.subList(0, (i + 1)));

            snapshots.add(snapshot);
            executorServiceSnapshots.execute(snapshot);
        }

        executorServiceSnapshots.shutdown();

        try {

            executorServiceSnapshots.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            new RuntimeException(e);
        }
    }

    private void executeAlgorithms() {

        snapshots.forEach(snapshot -> executorServiceAlgorithms.execute(new AlgorithmsSnapshotThread(snapshot)));

        executorServiceAlgorithms.shutdown();

        try {

            executorServiceAlgorithms.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        algorithmClasses.forEach(algorithmClass -> {

            algorithmsAveragePoints.put(algorithmClass, (double) algorithmsPointsSum.get(algorithmClass) / snapshots.size());
        });
    }

    public List<? extends Draw> getDraws() {

        return draws;
    }

    public Map<Class<? extends Algorithm>, Integer> getAlgorithmsPointsSum() {

        return algorithmsPointsSum;
    }

    public Map<Class<? extends Algorithm>, Integer> getAlgorithmsModePoints() {

        return algorithmsModePoints;
    }

    public Map<Class<? extends Algorithm>, Integer> getAlgorithmsMaximumPoints() {

        return algorithmsMaximumPoints;
    }

    public Map<Class<? extends Algorithm>, Double> getAlgorithmsAveragePoints() {

        return algorithmsAveragePoints;
    }

    public long getExecutionTime() {

        return executionTime;
    }

    public void showAlgorithmsPoints(String title) {

        System.out.println("Draws: " + draws.size() + ", Algorithms: " + algorithmClasses.size() + ", Execution Time: " + DurationFormatUtils.formatDuration(executionTime, "m'm' s's'") + ".");
        System.out.println("");
        System.out.println("                                                         ALGORITHM MODE MAX AVERAGE WINS  % STARS_DISTRIBUTED_FREQ          NUMBERS_DISTRIBUTED_FREQ                            DISTRIBUTED_FREQ");

        algorithmClasses.forEach(algorithmClass -> {

            System.out.println(String.format("%66s %4s %3s %7s %4s %2s %22s %33s %43s",
                algorithmClass.getSimpleName(),
                algorithmsModePoints.get(algorithmClass).toString(),
                algorithmsMaximumPoints.get(algorithmClass).toString(),
                DECIMAL_FORMAT_AVERAGE_POINTS.format(algorithmsAveragePoints.get(algorithmClass)),
                algorithmsWins.containsKey(algorithmClass) ? algorithmsWins.get(algorithmClass).toString() : "0",
                (int) (((double) (algorithmsWins.containsKey(algorithmClass) ? algorithmsWins.get(algorithmClass) : 0) / snapshots.size()) * 100),
                algorithmsStarsDistributedFreq.get(algorithmClass).toString(),
                algorithmsNumbersDistributedFreq.get(algorithmClass).toString(),
                algorithmsPoints.get(algorithmClass).toString()));
        });
    }

    class AlgorithmsSnapshotThread implements Runnable {

        private final Snapshot snapshot;

        AlgorithmsSnapshotThread(Snapshot snapshot) {

            this.snapshot = snapshot;
        }

        private List<Algorithm> getAlgorithms() {

            final List<Algorithm> algorithms = new LinkedList<>();

            algorithmClasses.forEach(algorithmClass -> {

                try {

                    algorithms.add(algorithmClass.getConstructor(Snapshot.class).newInstance(snapshot));

                } catch (Exception e) {

                    throw new RuntimeException(e);
                }
            });

            return algorithms;
        }

        @Override
        public void run() {

            final List<Algorithm> algorithms = getAlgorithms();

            for (Algorithm algorithm : algorithms) {

                final Class<? extends Algorithm> algorithmClass = algorithm.getClass();

                final Bet bet = algorithm.getNextBet();

                final int points = bet.getPoints(snapshot.getLastDraw());
                final int starsPoints = bet.getStarsPoints(snapshot.getLastDraw());
                final int numbersPoints = bet.getNumbersPoints(snapshot.getLastDraw());

                if (!algorithmsPoints.containsKey(algorithmClass)) {
                    algorithmsPoints.put(algorithmClass, new ConcurrentHashMap<>());
                }

                if (!algorithmsStarsDistributedFreq.containsKey(algorithmClass)) {
                    algorithmsStarsDistributedFreq.put(algorithmClass, new ConcurrentHashMap<>());
                }

                if (!algorithmsNumbersDistributedFreq.containsKey(algorithmClass)) {
                    algorithmsNumbersDistributedFreq.put(algorithmClass, new ConcurrentHashMap<>());
                }

                algorithmsPoints.get(algorithmClass).put(points,
                    algorithmsPoints.get(algorithmClass).containsKey(points) ? algorithmsPoints.get(algorithmClass).get(points) + 1 : 1);

                algorithmsStarsDistributedFreq.get(algorithmClass).put(starsPoints,
                    algorithmsStarsDistributedFreq.get(algorithmClass).containsKey(starsPoints) ? algorithmsStarsDistributedFreq.get(algorithmClass).get(starsPoints) + 1 : 1);

                algorithmsNumbersDistributedFreq.get(algorithmClass).put(numbersPoints,
                    algorithmsNumbersDistributedFreq.get(algorithmClass).containsKey(numbersPoints) ? algorithmsNumbersDistributedFreq.get(algorithmClass).get(numbersPoints) + 1 : 1);

                algorithmsPointsSum.put(algorithm.getClass(),
                    algorithmsPointsSum.containsKey(algorithm.getClass()) ? algorithmsPointsSum.get(algorithm.getClass()) + points : points);

                if (bet.isWinner(snapshot.getLastDraw())) {
                    algorithmsWins.put(algorithm.getClass(), algorithmsWins.containsKey(algorithm.getClass()) ? algorithmsWins.get(algorithm.getClass()) + 1 : 1);
                }

                if (!algorithmsMaximumPoints.containsKey(algorithm.getClass()) || points > algorithmsMaximumPoints.get(algorithm.getClass())) {
                    algorithmsMaximumPoints.put(algorithm.getClass(), points);
                }
            }

            for (Algorithm algorithm : algorithms) {

                Integer algorithmMode = null;

                int algorithmPointsMaxFreq = 0;

                for (Integer points : algorithmsPoints.get(algorithm.getClass()).keySet()) {

                    if (algorithmsPoints.get(algorithm.getClass()).get(points) > algorithmPointsMaxFreq) {

                        algorithmMode = points;

                        algorithmPointsMaxFreq = algorithmsPoints.get(algorithm.getClass()).get(points);
                    }
                }

                algorithmsModePoints.put(algorithm.getClass(), algorithmMode);
            }
        }
    }
}
