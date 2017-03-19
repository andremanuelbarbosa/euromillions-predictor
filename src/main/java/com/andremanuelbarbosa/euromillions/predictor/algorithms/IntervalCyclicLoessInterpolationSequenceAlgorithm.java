package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.CyclicLoessInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalCyclicLoessInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalCyclicLoessInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ICLoIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new CyclicLoessInterpolationSequenceAlgorithm(draws, drawStats).getNextValue() / draws.size());
    }
}
