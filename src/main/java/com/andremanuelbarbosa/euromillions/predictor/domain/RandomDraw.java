package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.Date;

public class RandomDraw extends Draw {

    public RandomDraw(long id, Date date, double prize) {
        super((int) id, date, prize);
    }
}
