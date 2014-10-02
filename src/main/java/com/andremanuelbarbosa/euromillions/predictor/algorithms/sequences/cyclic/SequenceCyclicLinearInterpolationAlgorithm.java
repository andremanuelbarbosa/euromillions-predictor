package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic;

import java.util.List;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

public class SequenceCyclicLinearInterpolationAlgorithm extends SequenceCyclicInterpolationAlgorithm {

  public SequenceCyclicLinearInterpolationAlgorithm(Item item, List<? extends Draw> draws) {

    super(item, draws);
  }

  protected void calculateNextValue() {

    loadValuesPolynomial();

    if (valuesPolynomialX.length < 2) {

      nextValue = draws.size();

    } else {

      nextValue = new LinearInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(
          item.getIntervals().size());
    }
  }
}