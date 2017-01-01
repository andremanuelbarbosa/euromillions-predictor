package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.CyclicLinearInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class IntervalCyclicLinearInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalCyclicLinearInterpolationSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return (new CyclicLinearInterpolationSequenceAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot().getDraws().size();
    }
}
