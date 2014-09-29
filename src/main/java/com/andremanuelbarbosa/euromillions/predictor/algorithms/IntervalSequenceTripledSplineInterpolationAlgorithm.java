package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.SequenceTripledSplineInterpolationAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalSequenceTripledSplineInterpolationAlgorithm extends Algorithm {

  public IntervalSequenceTripledSplineInterpolationAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return 1 - ((new SequenceTripledSplineInterpolationAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot()
        .getDraws().size());
  }
}
