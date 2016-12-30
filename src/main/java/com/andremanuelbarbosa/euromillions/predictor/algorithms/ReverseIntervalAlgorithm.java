package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseIntervalAlgorithm extends Algorithm {

    public ReverseIntervalAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return item.getMaximumInterval(getSnapshot().getDraws().size()) - item.getInterval();
    }
}
