package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.TripledLinearInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalTripledLinearInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalTripledLinearInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ITLIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new TripledLinearInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
