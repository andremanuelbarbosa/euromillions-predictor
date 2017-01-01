package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import java.util.List;

import org.apache.commons.math3.analysis.interpolation.LoessInterpolator;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

public class LoessInterpolationSequenceAlgorithm extends InterpolationSequenceAlgorithm {

  public LoessInterpolationSequenceAlgorithm(Item item, List<? extends Draw> draws) {

    super(item, draws);
  }

  protected void calculateNextValue() {

    loadValuesPolynomial();

    if (valuesPolynomialX.length < 7) {

      nextValue = (double) draws.size();

    } else {

      nextValue = new LoessInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(
          item.getIntervals().size());

      if (Double.isNaN(nextValue)) {

        nextValue = (double) draws.size();
      }
    }
  }
}
