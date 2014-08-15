package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;

public class TimeMachine {

  private static final int MINIMUM_DRAWS_INDEX = 0;

  private final Map<Class<? extends Algorithm>, Integer> algorithmsPoints = new HashMap<Class<? extends Algorithm>, Integer>();

  private final List<Snapshot> snapshots = new LinkedList<>();

  private final List<Draw> draws;

  private int maximumPoints = 0;

  private Algorithm maximumPointsAlgorithm;

  public TimeMachine(List<Draw> draws) {

    this.draws = draws;

    loadSnapshots();
  }

  private void loadSnapshots() {

    for (int i = MINIMUM_DRAWS_INDEX; i < (draws.size() - 1); i++) {

      Snapshot snapshot = new Snapshot(draws.subList(MINIMUM_DRAWS_INDEX, i + 1));

      snapshots.add(snapshot);
      executeAlgorithms(snapshot);
    }

    System.out.println("Maximum Points [" + maximumPointsAlgorithm.getClass().getName() + "," + maximumPoints + "]");
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

      int points = bet.getPoints(getLastDraw());

      algorithmsPoints.put(algorithm.getClass(),
          algorithmsPoints.containsKey(algorithm.getClass()) ? algorithmsPoints.get(algorithm.getClass()) + points
              : points);

      if (points > maximumPoints) {

        maximumPoints = points;

        maximumPointsAlgorithm = algorithm;
      }
    }

    System.out.println(algorithmsPoints.toString());
  }

  public Draw getLastDraw() {

    return draws.get(draws.size() - 1);
  }

  public Algorithm getMaximumPointsAlgorithm() {

    return maximumPointsAlgorithm;
  }
}
