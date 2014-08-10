package com.andremanuelbarbosa.euromillions.predictor.domain;

public class Star extends Item {

  public static final int COUNT = 11;

  public Star(int id, double relativeFreq) {

    super(id, ItemType.STAR, relativeFreq);
  }
}
