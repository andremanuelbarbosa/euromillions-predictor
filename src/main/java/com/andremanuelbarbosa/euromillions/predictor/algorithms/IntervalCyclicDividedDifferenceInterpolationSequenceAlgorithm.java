package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.cyclic.CyclicDividedDifferenceInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalCyclicDividedDifferenceInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalCyclicDividedDifferenceInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ICDDIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new CyclicDividedDifferenceInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
