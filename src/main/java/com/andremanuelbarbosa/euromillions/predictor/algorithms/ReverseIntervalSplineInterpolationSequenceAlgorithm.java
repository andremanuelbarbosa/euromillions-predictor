package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.SplineInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseIntervalSplineInterpolationSequenceAlgorithm extends Algorithm {

    public ReverseIntervalSplineInterpolationSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return 1 - ((new SplineInterpolationSequenceAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot().getDraws().size());
    }
}
