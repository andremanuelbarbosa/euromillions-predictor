package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class RelativeFreqSequenceAlgorithm extends SequenceAlgorithm {

    public RelativeFreqSequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

        super(draws, drawStats);
    }

    protected void calculateNextValue() {

        nextValue = 0.0;

        Integer minimumFreq = drawStats.getIntervals().size();

        for (Integer value : valuesFreq.keySet()) {

            if (valuesFreq.get(value) < minimumFreq) {

                minimumFreq = valuesFreq.get(value);

                nextValue = (double) valuesFreq.get(value) / drawStats.getIntervals().size();
            }
        }
    }
}
