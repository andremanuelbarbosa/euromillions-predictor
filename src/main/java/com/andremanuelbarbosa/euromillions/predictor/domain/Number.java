package com.andremanuelbarbosa.euromillions.predictor.domain;

public class Number extends Item {

  public Number(int id, double relativeFreq) {

    super(id, ItemType.NUMBER, relativeFreq);
  }
}
