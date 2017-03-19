package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;

import java.util.List;

public class RelativeIntervalAlgorithm extends Algorithm {

    public RelativeIntervalAlgorithm(Boolean reverse) {

        super("RI", reverse);
    }

    @Override
    public double getItemWeight(List<Draw> draws, DrawStats drawStats) {

        return drawStats.getInterval() / drawStats.getAverageInterval();
    }
}
