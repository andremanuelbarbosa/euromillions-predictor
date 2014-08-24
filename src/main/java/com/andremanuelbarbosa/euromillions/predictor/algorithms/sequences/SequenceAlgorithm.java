package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

public abstract class SequenceAlgorithm {

  final Item item;
  final List<? extends Draw> draws;

  final Map<Integer, Integer> valuesFreq = new HashMap<>();

  double nextValue;

  public SequenceAlgorithm(Item item, List<? extends Draw> draws) {

    this.item = item;
    this.draws = draws;

    loadValuesFreq();
    calculateNextValue();
  }

  abstract void calculateNextValue();

  public double getNextValue() {

    return nextValue;
  }

  Integer getMaximumValue() {

    Integer maximumValue = 0;

    for (Integer value : item.getIntervals()) {

      if (value > maximumValue) {

        maximumValue = value;
      }
    }

    return maximumValue;
  }

  public Map<Integer, Integer> getValuesFreq() {

    return valuesFreq;
  }

  void loadValuesFreq() {

    for (Integer value : item.getIntervals()) {

      if (!valuesFreq.containsKey(value)) {

        valuesFreq.put(value, 1);

      } else {

        valuesFreq.put(value, valuesFreq.get(value) + 1);
      }
    }
  }
}
