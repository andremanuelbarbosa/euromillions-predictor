package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;

public class TimeMachine {

  private static final DecimalFormat DECIMAL_FORMAT_AVERAGE_POINTS = new DecimalFormat("0.00");

  private final Map<Class<? extends Algorithm>, Integer> algorithmsPointsSum = new HashMap<Class<? extends Algorithm>, Integer>();
  private final Map<Class<? extends Algorithm>, Integer> algorithmsMaximumPoints = new HashMap<Class<? extends Algorithm>, Integer>();
  private final Map<Class<? extends Algorithm>, Double> algorithmsAveragePoints = new HashMap<Class<? extends Algorithm>, Double>();

  private final List<Snapshot> snapshots = new LinkedList<>();

  private final List<? extends Draw> draws;
  private final int minimumDrawsIndex;

  public TimeMachine(List<? extends Draw> draws, int minimumDrawsIndex) {

    this.draws = draws;
    this.minimumDrawsIndex = minimumDrawsIndex;

    loadSnapshots();
  }

  private void loadSnapshots() {

    for (int i = minimumDrawsIndex; i < (draws.size() - 1); i++) {

      Snapshot snapshot = new Snapshot(draws.subList(0, (i + 1)));

      snapshots.add(snapshot);
      executeAlgorithms(snapshot);
    }

    for (Class<? extends Algorithm> algorithmClass : algorithmsPointsSum.keySet()) {

      algorithmsAveragePoints.put(algorithmClass, (double) algorithmsPointsSum.get(algorithmClass) / snapshots.size());
    }
  }

  private List<Algorithm> getAlgorithms(Snapshot snapshot) {

    List<Algorithm> algorithms = new LinkedList<>();

    Reflections reflections = new Reflections("com.andremanuelbarbosa.euromillions.predictor.algorithms");

    Set<Class<? extends Algorithm>> algorithmClasses = reflections.getSubTypesOf(Algorithm.class);

    for (Class<? extends Algorithm> algorithmClass : algorithmClasses) {

      try {

        algorithms.add(algorithmClass.getConstructor(Snapshot.class).newInstance(snapshot));

      } catch (Exception e) {

        throw new RuntimeException(e);
      }
    }

    return algorithms;
  }

  private void executeAlgorithms(Snapshot snapshot) {

    List<Algorithm> algorithms = getAlgorithms(snapshot);

    for (Algorithm algorithm : algorithms) {

      Bet bet = algorithm.getNextBet();

      int points = bet.getPoints(snapshot.getLastDraw());

      algorithmsPointsSum.put(algorithm.getClass(),
          algorithmsPointsSum.containsKey(algorithm.getClass()) ? algorithmsPointsSum.get(algorithm.getClass())
              + points : points);

      if (!algorithmsMaximumPoints.containsKey(algorithm.getClass())
          || points > algorithmsMaximumPoints.get(algorithm.getClass())) {

        algorithmsMaximumPoints.put(algorithm.getClass(), points);
      }
    }
  }

  public List<? extends Draw> getDraws() {

    return draws;
  }

  public Map<Class<? extends Algorithm>, Integer> getAlgorithmsPointsSum() {

    return algorithmsPointsSum;
  }

  public Map<Class<? extends Algorithm>, Integer> getAlgorithmsMaximumPoints() {

    return algorithmsMaximumPoints;
  }

  public Map<Class<? extends Algorithm>, Double> getAlgorithmsAveragePoints() {

    return algorithmsAveragePoints;
  }

  public void showAlgorithmsPoints() {

    for (Class<? extends Algorithm> algorithmClass : algorithmsPointsSum.keySet()) {

      System.out.println(String.format("%32s %3s %s %s", algorithmClass.getSimpleName(),
          algorithmsPointsSum.get(algorithmClass).toString(), algorithmsMaximumPoints.get(algorithmClass).toString(),
          DECIMAL_FORMAT_AVERAGE_POINTS.format(algorithmsAveragePoints.get(algorithmClass))));
    }
  }
}
