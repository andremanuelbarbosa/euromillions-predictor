package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.SequenceCyclicLinearInterpolationAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceCyclicLinearInterpolationAlgorithm extends Algorithm {

  public IntervalSequenceCyclicLinearInterpolationAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - ((new SequenceCyclicLinearInterpolationAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot()
        .getDraws().size());
  }
}
