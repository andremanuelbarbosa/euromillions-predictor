package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseRelativeFreqAndRelativeIntervalAlgorithm extends Algorithm {

    public ReverseRelativeFreqAndRelativeIntervalAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return (1 - item.getRelativeFreq()) * (item.getInterval() / item.getAverageInterval(getSnapshot().getDraws().size()));
    }
}
