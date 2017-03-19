package com.andremanuelbarbosa.euromillions.predictor.domain;

public class DrawPrize {

    private final int stars;
    private final int numbers;
    private final double prize;

    public DrawPrize(int stars, int numbers, double prize) {

        this.stars = stars;
        this.numbers = numbers;
        this.prize = prize;
    }

    public int getStars() {

        return stars;
    }

    public int getNumbers() {

        return numbers;
    }

    public double getPrize() {

        return prize;
    }
}
