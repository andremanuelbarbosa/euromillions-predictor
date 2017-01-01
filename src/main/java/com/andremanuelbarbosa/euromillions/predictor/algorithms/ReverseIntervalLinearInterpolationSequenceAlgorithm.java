package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.LinearInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseIntervalLinearInterpolationSequenceAlgorithm extends Algorithm {

    public ReverseIntervalLinearInterpolationSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return 1 - ((new LinearInterpolationSequenceAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot().getDraws().size());
    }
}
