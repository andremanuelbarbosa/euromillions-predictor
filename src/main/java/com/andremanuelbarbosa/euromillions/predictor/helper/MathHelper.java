package com.andremanuelbarbosa.euromillions.predictor.helper;

public abstract class MathHelper {

    public static double getDoubleWithTwoDecimalPlaces(double rawDouble) {

        return Math.round(rawDouble * 100.0) / 100.0;
    }
}
