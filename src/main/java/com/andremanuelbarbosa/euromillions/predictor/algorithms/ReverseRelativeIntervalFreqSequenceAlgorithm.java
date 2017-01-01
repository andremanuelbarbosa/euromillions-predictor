package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.RelativeFreqSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseRelativeIntervalFreqSequenceAlgorithm extends Algorithm {

    public ReverseRelativeIntervalFreqSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return 1 - (new RelativeFreqSequenceAlgorithm(item, getSnapshot().getDraws())).getNextValue();
    }
}
