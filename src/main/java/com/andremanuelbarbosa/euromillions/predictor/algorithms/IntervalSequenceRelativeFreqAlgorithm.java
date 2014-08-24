package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.SequenceRelativeFreqAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceRelativeFreqAlgorithm extends Algorithm {

  public IntervalSequenceRelativeFreqAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - (new SequenceRelativeFreqAlgorithm(item, getSnapshot().getDraws())).getNextValue();
  }
}
