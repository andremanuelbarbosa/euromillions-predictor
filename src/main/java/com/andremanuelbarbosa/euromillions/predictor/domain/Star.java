package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.List;

public class Star extends Item {

  private static final double AVERAGE_INTERVAL = 4.5;

  public static final int COUNT = 11;

  public Star(int id, int interval, double relativeFreq, List<Integer> intervals) {

    super(id, ItemType.STAR, interval, relativeFreq, intervals);
  }

  @Override
  public double getAverageInterval() {

    return AVERAGE_INTERVAL;
  }
}
