package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.RelativeFreqSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class RelativeIntervalFreqSequenceAlgorithm extends Algorithm {

    public RelativeIntervalFreqSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return (new RelativeFreqSequenceAlgorithm(item, getSnapshot().getDraws())).getNextValue();
    }
}
