package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class RelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm extends ReverseRelativeIntervalFreqSequenceAlgorithm {

    public RelativeFreqAndReverseRelativeIntervalFreqSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return item.getRelativeFreq() * super.getItemWeight(item);
    }
}
