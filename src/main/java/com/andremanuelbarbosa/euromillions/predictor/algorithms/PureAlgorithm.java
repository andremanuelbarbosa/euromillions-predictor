package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class PureAlgorithm extends Algorithm {

  public PureAlgorithm(Snapshot snapshot) {

    super(snapshot, true);
  }

  @Override
  double getItemWeight(Item item) {

    return (double) 1 / (item.getItemType() == ItemType.STAR ? Star.COUNT : Number.COUNT);
  }
}
