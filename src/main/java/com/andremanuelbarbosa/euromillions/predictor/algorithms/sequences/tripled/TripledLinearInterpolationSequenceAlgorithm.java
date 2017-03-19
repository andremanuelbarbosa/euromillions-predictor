package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;

import java.util.List;

public class TripledLinearInterpolationSequenceAlgorithm extends TripledInterpolationSequenceAlgorithm {

    public TripledLinearInterpolationSequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

        super(draws, drawStats);
    }

    protected void calculateNextValue() {

        loadValuesPolynomial();

        if (valuesPolynomialX.length < 2) {

            nextValue = draws.size();

        } else {

            nextValue = new LinearInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(drawStats.getIntervals().size());
        }
    }
}
