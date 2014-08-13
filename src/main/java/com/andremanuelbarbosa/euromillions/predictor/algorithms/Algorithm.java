package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.Random;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;

public abstract class Algorithm {

  static final Random RANDOM = new Random();

  private final boolean randomized;

  public Algorithm(boolean randomized) {

    this.randomized = randomized;
  }

  public boolean isRandomized() {

    return randomized;
  }

  public abstract Bet getNextBet();
}
