package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import java.util.List;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

public class SequenceLinearInterpolationAlgorithm extends SequenceAlgorithm {

  public SequenceLinearInterpolationAlgorithm(Item item, List<? extends Draw> draws) {

    super(item, draws);
  }

  void calculateNextValue() {

    if (item.getIntervals().size() < 2) {

      nextValue = draws.size();

    } else {

      double[] valuesPolynomialX = new double[item.getIntervals().size() * 2];
      double[] valuesPolynomialY = new double[item.getIntervals().size() * 2];

      for (int i = 0; i < item.getIntervals().size(); i++) {

        valuesPolynomialX[i] = i;
        valuesPolynomialY[i] = item.getIntervals().get(i);
      }

      for (int i = item.getIntervals().size(); i < (item.getIntervals().size() * 2); i++) {

        valuesPolynomialX[i] = i + 1;
        valuesPolynomialY[i] = item.getAverageInterval();
      }

      nextValue = new LinearInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(
          item.getIntervals().size());
    }
  }
}
