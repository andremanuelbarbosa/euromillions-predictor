package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalAlgorithm extends Algorithm {

  public IntervalAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return (double) (item.getInterval() + 1)
        / ((item.getItemType() == ItemType.STAR ? snapshot.getStarsMaximumInterval() : snapshot
            .getNumbersMaximumInterval()) + 2);
  }
}
