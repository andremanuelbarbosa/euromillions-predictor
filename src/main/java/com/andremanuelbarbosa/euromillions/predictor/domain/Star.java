package com.andremanuelbarbosa.euromillions.predictor.domain;

public class Star extends Item {

  public static final int COUNT = 11;

  public Star(int id, int interval, double relativeFreq) {

    super(id, ItemType.STAR, interval, relativeFreq);
  }
}
