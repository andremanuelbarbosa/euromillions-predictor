package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.List;

public class Number extends Item {

    public static final int COUNT = 50;
    public static final int COUNT_PER_DRAW = 5;

    public Number(int id, int interval, int freq, double relativeFreq, List<Integer> intervals) {

        super(id, ItemType.NUMBER, interval, freq, relativeFreq, intervals);
    }

    @Override
    public double getAverageInterval(int numDraws) {

        return (double) COUNT / COUNT_PER_DRAW;
    }
}
