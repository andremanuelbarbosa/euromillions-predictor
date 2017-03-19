package com.andremanuelbarbosa.euromillions.predictor.domain;

public class BetStats {

    private final double costs;
    private final double earnings;
    private final double balance;

    public BetStats(double costs, double earnings, double balance) {

        this.costs = costs;
        this.earnings = earnings;
        this.balance = balance;
    }

    public double getCosts() {

        return costs;
    }

    public double getEarnings() {

        return earnings;
    }

    public double getBalance() {

        return balance;
    }
}
