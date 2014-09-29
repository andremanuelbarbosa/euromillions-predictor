package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.SequenceTripledLoessInterpolationAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceTripledLoessInterpolationAlgorithm extends Algorithm {

  public IntervalSequenceTripledLoessInterpolationAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - ((new SequenceTripledLoessInterpolationAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot()
        .getDraws().size());
  }
}
