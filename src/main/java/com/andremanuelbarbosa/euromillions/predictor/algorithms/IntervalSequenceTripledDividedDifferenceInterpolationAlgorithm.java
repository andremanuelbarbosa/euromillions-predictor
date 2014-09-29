package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.SequenceTripledDividedDifferenceInterpolationAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceTripledDividedDifferenceInterpolationAlgorithm extends Algorithm {

  public IntervalSequenceTripledDividedDifferenceInterpolationAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - ((new SequenceTripledDividedDifferenceInterpolationAlgorithm(item, getSnapshot().getDraws()))
        .getNextValue() / getSnapshot().getDraws().size());
  }
}
