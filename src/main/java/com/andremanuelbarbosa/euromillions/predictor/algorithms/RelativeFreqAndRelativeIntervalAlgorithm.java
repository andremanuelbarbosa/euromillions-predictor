package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class RelativeFreqAndRelativeIntervalAlgorithm extends Algorithm {

    public RelativeFreqAndRelativeIntervalAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return item.getRelativeFreq() * (item.getInterval() / item.getAverageInterval(getSnapshot().getDraws().size()));
    }
}
