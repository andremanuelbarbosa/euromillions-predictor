package com.andremanuelbarbosa.euromillions.predictor.domain;

import static com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot.DRAWS_COUNT_BEFORE_ELEVEN_STARS;
import static com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot.DRAWS_COUNT_BEFORE_TWELVE_STARS;

public enum ItemType {

    NUMBER, STAR;

    public double getAverageInterval(int numDraws) {

        if (this == STAR) {

            return (double) (numDraws <= DRAWS_COUNT_BEFORE_ELEVEN_STARS ? 10 : (numDraws <= DRAWS_COUNT_BEFORE_TWELVE_STARS ? 11 : 12)) / Star.COUNT_PER_DRAW;

        } else {

            return (double) Number.COUNT / Number.COUNT_PER_DRAW;
        }
    }
}
