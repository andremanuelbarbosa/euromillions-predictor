package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.SequenceCyclicLoessInterpolationAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceCyclicLoessInterpolationAlgorithm extends Algorithm {

  public IntervalSequenceCyclicLoessInterpolationAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - ((new SequenceCyclicLoessInterpolationAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot()
        .getDraws().size());
  }
}
