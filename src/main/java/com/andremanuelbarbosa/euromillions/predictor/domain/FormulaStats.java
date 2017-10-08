package com.andremanuelbarbosa.euromillions.predictor.domain;

public class FormulaStats {

    private final int drawId;
    private final String formulaName;
    private final double costs;
    private final String points;
    private final double winnings;
    private final double earnings;
    private final double earningsPercentage;

    public FormulaStats(int drawId, String formulaName, double costs, String points, double winnings, double earnings, double earningsPercentage) {

        this.drawId = drawId;
        this.formulaName = formulaName;
        this.costs = costs;
        this.points = points;
        this.winnings = winnings;
        this.earnings = earnings;
        this.earningsPercentage = earningsPercentage;
    }

    public int getDrawId() {

        return drawId;
    }

    public String getFormulaName() {

        return formulaName;
    }

    public double getCosts() {
        return costs;
    }

    public String getPoints() {

        return points;
    }

    public double getWinnings() {

        return winnings;
    }

    public double getEarnings() {

        return earnings;
    }

    public double getEarningsPercentage() {

        return earningsPercentage;
    }
}
