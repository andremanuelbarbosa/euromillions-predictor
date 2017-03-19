package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

abstract class InterpolationSequenceAlgorithm extends SequenceAlgorithm {

    double[] valuesPolynomialX;
    double[] valuesPolynomialY;

    public InterpolationSequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

        super(draws, drawStats);
    }

    void loadValuesPolynomial() {

        valuesPolynomialX = new double[drawStats.getIntervals().size() * 2];
        valuesPolynomialY = new double[drawStats.getIntervals().size() * 2];

        for (int i = 0; i < drawStats.getIntervals().size(); i++) {

            valuesPolynomialX[i] = i;
            valuesPolynomialY[i] = drawStats.getIntervals().get(i);
        }

        for (int i = drawStats.getIntervals().size(); i < (drawStats.getIntervals().size() * 2); i++) {

            valuesPolynomialX[i] = i + 1;
            valuesPolynomialY[i] = drawStats.getAverageInterval();
        }
    }
}
