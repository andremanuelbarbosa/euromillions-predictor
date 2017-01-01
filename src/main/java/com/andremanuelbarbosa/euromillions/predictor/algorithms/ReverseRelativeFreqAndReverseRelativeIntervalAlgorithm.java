package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseRelativeFreqAndReverseRelativeIntervalAlgorithm extends Algorithm {

    public ReverseRelativeFreqAndReverseRelativeIntervalAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return (1 - item.getRelativeFreq()) * (1 - (item.getInterval() / item.getAverageInterval(getSnapshot().getDraws().size())));
    }
}
