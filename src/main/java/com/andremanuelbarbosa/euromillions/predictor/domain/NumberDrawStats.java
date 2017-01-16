package com.andremanuelbarbosa.euromillions.predictor.domain;

public class NumberDrawStats extends DrawStats {

    private final int number;

    public NumberDrawStats(int drawId, int freq, int interval, double relativeFreq, double averageInterval, int number) {

        super(drawId, freq, interval, relativeFreq, averageInterval);

        this.number = number;
    }

    public int getNumber() {

        return number;
    }
}
