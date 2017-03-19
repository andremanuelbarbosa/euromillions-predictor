package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class RelativeFreqAlgorithm extends Algorithm {

    public RelativeFreqAlgorithm(Boolean reverse) {

        super("RF", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return drawStats.getRelativeFreq();
    }
}
