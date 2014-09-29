package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.SequenceCyclicDividedDifferenceInterpolationAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceCyclicDividedDifferenceInterpolationAlgorithm extends Algorithm {

  public IntervalSequenceCyclicDividedDifferenceInterpolationAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - ((new SequenceCyclicDividedDifferenceInterpolationAlgorithm(item, getSnapshot().getDraws()))
        .getNextValue() / getSnapshot().getDraws().size());
  }
}
