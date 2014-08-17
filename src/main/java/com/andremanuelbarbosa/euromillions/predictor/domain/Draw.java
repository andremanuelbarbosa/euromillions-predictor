package com.andremanuelbarbosa.euromillions.predictor.domain;

public abstract class Draw extends Result {

  private int index;

  void setIndex(int index) {

    this.index = index;
  }

  public int getIndex() {

    return index;
  }
}
