package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.TripledLoessInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class IntervalTripledLoessInterpolationSequenceAlgorithm extends Algorithm {

    public IntervalTripledLoessInterpolationSequenceAlgorithm(Boolean reverse) {

        super("ITLoIS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new TripledLoessInterpolationSequenceAlgorithm(draws, drawStats)).getNextValue() / draws.size();
    }
}
