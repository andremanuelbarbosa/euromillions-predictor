package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class RelativeFreqAlgorithm extends Algorithm {

  public RelativeFreqAlgorithm(Snapshot snapshot) {

    super(snapshot, false);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - item.getRelativeFreq();
  }
}
