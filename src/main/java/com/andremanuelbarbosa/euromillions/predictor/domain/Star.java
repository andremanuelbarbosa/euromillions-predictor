package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.List;

import static com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot.DRAWS_COUNT_BEFORE_ELEVEN_STARS;
import static com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot.DRAWS_COUNT_BEFORE_TWELVE_STARS;

public class Star extends Item {

    public static final int COUNT = 12;
    public static final int COUNT_PER_DRAW = 2;

    public Star(int id, int interval, int freq, double relativeFreq, List<Integer> intervals) {

        super(id, ItemType.STAR, interval, freq, relativeFreq, intervals);
    }

    @Override
    public double getAverageInterval(int numDraws) {

        return (double) (numDraws <= DRAWS_COUNT_BEFORE_ELEVEN_STARS ? 10 : (numDraws <= DRAWS_COUNT_BEFORE_TWELVE_STARS ? 11 : 12)) / COUNT_PER_DRAW;
    }
}
