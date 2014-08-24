package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.HashMap;
import java.util.Map;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class IntervalSequenceRelativeFreqRandomAlgorithm extends IntervalSequenceRelativeFreqAlgorithm {

  private final Map<Item, Double> itemsWeight = new HashMap<>(Star.COUNT + Number.COUNT);

  public IntervalSequenceRelativeFreqRandomAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    if (!itemsWeight.containsKey(item)) {

      itemsWeight.put(item, RANDOM.nextDouble() * super.getItemWeight(item));
    }

    return itemsWeight.get(item);
  }
}
