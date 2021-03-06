package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;

import java.util.List;

public class CyclicSplineInterpolationSequenceAlgorithm extends CyclicInterpolationSequenceAlgorithm {

    public CyclicSplineInterpolationSequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

        super(draws, drawStats);
    }

    protected void calculateNextValue() {

        loadValuesPolynomial();

        if (valuesPolynomialX.length <= 2) {

            nextValue = draws.size();

        } else {

            nextValue = new SplineInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(drawStats.getIntervals().size());
        }
    }
}
