package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences.RelativeFreqSequenceAlgorithm;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class RelativeIntervalFreqSequenceAlgorithm extends Algorithm {

    public RelativeIntervalFreqSequenceAlgorithm(Boolean reverse) {

        super("RIFS", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return (new RelativeFreqSequenceAlgorithm(draws, drawStats)).getNextValue();
    }
}
