package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.LinkedList;
import java.util.List;

public abstract class DrawStats {

    private final int drawId;
    private final int freq;
    private final int interval;
    private final double relativeFreq;
    private final double averageInterval;

    private final List<Integer> intervals = new LinkedList<>();

    public DrawStats(int drawId, int freq, int interval, double relativeFreq, double averageInterval) {

        this.drawId = drawId;
        this.freq = freq;
        this.interval = interval;
        this.relativeFreq = relativeFreq;
        this.averageInterval = averageInterval;
    }

    public int getDrawId() {

        return drawId;
    }

    public int getFreq() {

        return freq;
    }

    public int getInterval() {

        return interval;
    }

    public double getRelativeFreq() {

        return relativeFreq;
    }

    public double getAverageInterval() {

        return averageInterval;
    }

    public List<Integer> getIntervals() {

        return intervals;
    }
}
