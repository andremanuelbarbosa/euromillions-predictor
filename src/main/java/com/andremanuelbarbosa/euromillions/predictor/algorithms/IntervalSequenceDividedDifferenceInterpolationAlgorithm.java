package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.SequenceDividedDifferenceInterpolationAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceDividedDifferenceInterpolationAlgorithm extends Algorithm {

  public IntervalSequenceDividedDifferenceInterpolationAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - ((new SequenceDividedDifferenceInterpolationAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot()
        .getDraws().size());
  }
}
