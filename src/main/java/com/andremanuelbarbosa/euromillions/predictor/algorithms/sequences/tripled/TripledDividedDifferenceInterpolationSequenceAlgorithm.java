package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import org.apache.commons.math3.analysis.interpolation.DividedDifferenceInterpolator;

import java.util.List;

public class TripledDividedDifferenceInterpolationSequenceAlgorithm extends TripledInterpolationSequenceAlgorithm {

    public TripledDividedDifferenceInterpolationSequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

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
