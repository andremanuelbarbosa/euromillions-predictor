package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class RelativeFreqAndIntervalSequenceRelativeFreqAlgorithm extends IntervalSequenceRelativeFreqAlgorithm {

  public RelativeFreqAndIntervalSequenceRelativeFreqAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return (1 - item.getRelativeFreq()) * super.getItemWeight(item);
  }
}
