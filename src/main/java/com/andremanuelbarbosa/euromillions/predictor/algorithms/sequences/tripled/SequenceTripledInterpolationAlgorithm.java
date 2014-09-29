package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled;

import java.util.List;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.SequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

abstract class SequenceTripledInterpolationAlgorithm extends SequenceAlgorithm {

  double[] valuesPolynomialX;
  double[] valuesPolynomialY;

  public SequenceTripledInterpolationAlgorithm(Item item, List<? extends Draw> draws) {

    super(item, draws);
  }

  void loadValuesPolynomial() {

    valuesPolynomialX = new double[item.getIntervals().size() * 3];
    valuesPolynomialY = new double[item.getIntervals().size() * 3];

    for (int i = 0; i < item.getIntervals().size(); i++) {

      valuesPolynomialX[i] = i;
      valuesPolynomialY[i] = item.getIntervals().get(i);
    }

    for (int i = item.getIntervals().size(); i < (item.getIntervals().size() * 3); i++) {

      valuesPolynomialX[i] = i + 1;
      valuesPolynomialY[i] = item.getAverageInterval();
    }
  }
}
