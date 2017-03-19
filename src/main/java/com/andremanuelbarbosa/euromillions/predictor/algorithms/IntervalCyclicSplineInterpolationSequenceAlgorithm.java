package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.CyclicSplineInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalCyclicSplineInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalCyclicSplineInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ICSIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new CyclicSplineInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
