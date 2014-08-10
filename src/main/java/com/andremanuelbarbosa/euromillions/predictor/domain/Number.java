package com.andremanuelbarbosa.euromillions.predictor.domain;

public class Number extends Item {

  public static final int COUNT = 50;

  public Number(int id, double relativeFreq) {

    super(id, ItemType.NUMBER, relativeFreq);
  }
}
