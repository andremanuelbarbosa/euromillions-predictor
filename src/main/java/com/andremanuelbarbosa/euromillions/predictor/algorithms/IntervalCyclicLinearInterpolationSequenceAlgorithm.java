package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.CyclicLinearInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalCyclicLinearInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalCyclicLinearInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ICLIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new CyclicLinearInterpolationSequenceAlgorithm(draws, drawStats).getNextValue() / draws.size());
    }
}
