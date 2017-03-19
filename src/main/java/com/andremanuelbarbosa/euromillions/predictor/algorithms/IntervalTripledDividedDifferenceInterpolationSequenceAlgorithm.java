package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.TripledDividedDifferenceInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalTripledDividedDifferenceInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalTripledDividedDifferenceInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ITDDIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new TripledDividedDifferenceInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
