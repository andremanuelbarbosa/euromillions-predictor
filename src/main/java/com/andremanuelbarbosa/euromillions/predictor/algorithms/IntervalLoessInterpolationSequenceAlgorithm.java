package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.LoessInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalLoessInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalLoessInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ILoIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new LoessInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
