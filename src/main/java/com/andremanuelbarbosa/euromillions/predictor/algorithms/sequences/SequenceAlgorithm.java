package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SequenceAlgorithm {

    protected final List<Draw> draws;
    protected final DrawStats drawStats;

    protected final Map<Integer, Integer> valuesFreq = new HashMap<>();

    protected double nextValue;

    public SequenceAlgorithm(List<Draw> draws, DrawStats drawStats) {

        this.draws = draws;
        this.drawStats = drawStats;

        loadValuesFreq();
        calculateNextValue();
    }

    protected abstract void calculateNextValue();

    public double getNextValue() {

        return nextValue;
    }

    Integer getMaximumValue() {

        Integer maximumValue = 0;

        for (Integer value : drawStats.getIntervals()) {

            if (value > maximumValue) {

                maximumValue = value;
            }
        }

        return maximumValue;
    }

    public Map<Integer, Integer> getValuesFreq() {

        return valuesFreq;
    }

    void loadValuesFreq() {

        for (Integer value : drawStats.getIntervals()) {

            valuesFreq.put(value, valuesFreq.containsKey(value) ? valuesFreq.get(value) + 1 : 1);
        }
    }
}
