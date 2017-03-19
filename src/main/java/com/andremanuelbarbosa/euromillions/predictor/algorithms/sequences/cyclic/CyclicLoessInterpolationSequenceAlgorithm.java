package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import org.apache.commons.math3.analysis.interpolation.LoessInterpolator;

import java.util.List;

public class CyclicLoessInterpolationSequenceAlgorithm extends CyclicInterpolationSequenceAlgorithm {

    public CyclicLoessInterpolationSequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

        super(draws, drawStats);
    }

    protected void calculateNextValue() {

        loadValuesPolynomial();

        if (valuesPolynomialX.length < 7) {

            nextValue = (double) draws.size();

        } else {

            nextValue = new LoessInterpolator().interpolate(valuesPolynomialX, valuesPolynomialY).value(drawStats.getIntervals().size());

            if (Double.isNaN(nextValue)) {

                nextValue = (double) draws.size();
            }
        }
    }
}
