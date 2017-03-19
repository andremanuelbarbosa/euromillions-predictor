package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.DividedDifferenceInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalDividedDifferenceInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalDividedDifferenceInterpolationSequenceAlgorithm(Boolean reverse) {

        super("IDDIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new DividedDifferenceInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
