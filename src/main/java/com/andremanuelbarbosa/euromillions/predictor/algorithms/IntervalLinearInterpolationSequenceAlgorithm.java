package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.LinearInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalLinearInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalLinearInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ILIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new LinearInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
