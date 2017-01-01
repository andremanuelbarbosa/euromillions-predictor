package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.tripled.TripledSplineInterpolationSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class ReverseIntervalTripledSplineInterpolationSequenceAlgorithm extends Algorithm {

    public ReverseIntervalTripledSplineInterpolationSequenceAlgorithm(Snapshot snapshot) {

        super(snapshot);
    }

    @Override
    double getItemWeight(Item item) {

        return 1 - ((new TripledSplineInterpolationSequenceAlgorithm(item, getSnapshot().getDraws())).getNextValue() / getSnapshot().getDraws().size());
    }
}
