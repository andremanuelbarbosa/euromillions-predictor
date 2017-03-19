package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator;

import java.util.List;

public class DividedDifferenceInterpolationSequenceAlgorithm extends InterpolationSequenceAlgorithm {

    public DividedDifferenceInterpolationSequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

        super(draws, drawStats);
    }

    protected void calculateNextValue() {

        loadValuesPolynomial();

        if (valuesPolynomialX.length < 2) {

            nextValue = draws.size();

        } else {

            nextValue = new DividedDifferenceInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(drawStats.getIntervals().size());
        }
    }
}
