package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.SplineInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalSplineInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalSplineInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ISIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new SplineInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
