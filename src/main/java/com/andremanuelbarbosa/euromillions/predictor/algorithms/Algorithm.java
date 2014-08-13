package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.Random;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public abstract class Algorithm {

  static final Random RANDOM = new Random();

  final Snapshot snapshot;

  private final boolean randomized;

  public Algorithm(Snapshot snapshot, boolean randomized) {

    this.snapshot = snapshot;

    this.randomized = randomized;
  }

  public boolean isRandomized() {

    return randomized;
  }

  public abstract Bet getNextBet();
}
