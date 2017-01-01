package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.DividedDifferenceInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalDividedDifferenceInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalDividedDifferenceInterpolationSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return (new DividedDifferenceInterpolationSequenceAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot().getDraws().size();
    }
}
