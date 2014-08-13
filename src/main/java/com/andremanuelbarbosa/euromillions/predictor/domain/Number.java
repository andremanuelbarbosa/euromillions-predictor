package com.andremanuelbarbosa.euromillions.predictor.domain;

public class Number extends Item {

  public static final int COUNT = 50;

  public Number(int id, int interval, double relativeFreq) {

    super(id, ItemType.NUMBER, interval, relativeFreq);
  }
}
