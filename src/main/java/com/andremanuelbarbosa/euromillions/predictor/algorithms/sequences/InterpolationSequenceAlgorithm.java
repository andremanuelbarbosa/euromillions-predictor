package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import java.util.List;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

abstract class InterpolationSequenceAlgorithm extends SequenceAlgorithm {

    double[] valuesPolynomialX;
    double[] valuesPolynomialY;

    public InterpolationSequenceAlgorithm(Item item, List<? extends Draw> draws) {

        super(item, draws);
    }

    void loadValuesPolynomial() {

        valuesPolynomialX = new double[item.getIntervals().size() * 2];
        valuesPolynomialY = new double[item.getIntervals().size() * 2];

        for (int i = 0; i < item.getIntervals().size(); i++) {

            valuesPolynomialX[i] = i;
            valuesPolynomialY[i] = item.getIntervals().get(i);
        }

        for (int i = item.getIntervals().size(); i < (item.getIntervals().size() * 2); i++) {

            valuesPolynomialX[i] = i + 1;
            valuesPolynomialY[i] = item.getAverageInterval(draws.size());
        }
    }
}
