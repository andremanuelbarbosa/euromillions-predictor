package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.TripledSplineInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalTripledSplineInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalTripledSplineInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ITSIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new TripledSplineInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
