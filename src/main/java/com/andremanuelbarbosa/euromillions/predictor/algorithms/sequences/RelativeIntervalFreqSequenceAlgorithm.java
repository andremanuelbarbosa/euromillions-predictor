package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import java.util.List;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;

public class RelativeIntervalFreqSequenceAlgorithm extends SequenceAlgorithm {

    public RelativeIntervalFreqSequenceAlgorithm(Item item, List<? extends Draw> draws) {

        super(item, draws);
    }

    protected void calculateNextValue() {

        nextValue = 0.0;

        Integer minimumFreq = item.getIntervals().size();

        for (Integer value : valuesFreq.keySet()) {

            if (valuesFreq.get(value) < minimumFreq) {

                minimumFreq = valuesFreq.get(value);

                nextValue = (double) valuesFreq.get(value) / item.getIntervals().size();
            }
        }
    }
}
