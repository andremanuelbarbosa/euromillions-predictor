package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseFreqAlgorithm extends Algorithm {

  public ReverseFreqAlgorithm(Snapshot snapshot) {

    super(snapshot, false);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - item.getRelativeFreq();
  }
}
