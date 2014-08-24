package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.List;

public class Number extends Item {

  private static final double AVERAGE_INTERVAL = 10.0;

  public static final int COUNT = 50;

  public Number(int id, int interval, double relativeFreq, List<Integer> intervals) {

    super(id, ItemType.NUMBER, interval, relativeFreq, intervals);
  }

  @Override
  public double getAverageInterval() {

    return AVERAGE_INTERVAL;
  }
}
