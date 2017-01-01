package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import java.util.List;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

public class SplineInterpolationSequenceAlgorithm extends InterpolationSequenceAlgorithm {

    public SplineInterpolationSequenceAlgorithm(Item item, List<? extends Draw> draws) {

        super(item, draws);
    }

    protected void calculateNextValue() {

        loadValuesPolynomial();

        if (valuesPolynomialX.length < 3) {

            nextValue = draws.size();

        } else {

            nextValue = new SplineInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(item.getIntervals().size());
        }
    }
}
