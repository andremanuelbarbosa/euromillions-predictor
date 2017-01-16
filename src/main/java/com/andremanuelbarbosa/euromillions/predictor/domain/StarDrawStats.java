package com.andremanuelbarbosa.euromillions.predictor.domain;

public class StarDrawStats extends DrawStats {

    private final int star;

    public StarDrawStats(int drawId, int freq, int interval, double relativeFreq, double averageInterval, int star) {

        super(drawId, freq, interval, relativeFreq, averageInterval);

        this.star = star;
    }

    public int getStar() {

        return star;
    }
}
