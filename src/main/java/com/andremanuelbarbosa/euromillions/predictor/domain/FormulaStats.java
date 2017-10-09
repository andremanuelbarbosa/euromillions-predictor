package com.andremanuelbarbosa.euromillions.predictor.domain;

public class FormulaStats {

    private final int drawId;
    private final String formulaName;
    private final double costs;
    private final String points;
    private final double winnings;
    private final double earnings;
    private final double earningsFactor;

    public FormulaStats(int drawId, String formulaName, double costs, String points, double winnings, double earnings, double earningsFactor) {

        this.drawId = drawId;
        this.formulaName = formulaName;
        this.costs = costs;
        this.points = points;
        this.winnings = winnings;
        this.earnings = earnings;
        this.earningsFactor = earningsFactor;
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

    public double getEarningsFactor() {

        return earningsFactor;
    }

    public static class Formula {

        private final String name;
        private final int draws;
        private final double costs;
        private final double winnings;
        private final double earnings;
        private final double earningsFactor;

        public Formula(String name, int draws, double costs, double winnings, double earnings, double earningsFactor) {

            this.name = name;
            this.draws = draws;
            this.costs = costs;
            this.winnings = winnings;
            this.earnings = earnings;
            this.earningsFactor = earningsFactor;
        }

        public String getName() {

            return name;
        }

        public int getDraws() {

            return draws;
        }

        public double getCosts() {

            return costs;
        }

        public double getWinnings() {

            return winnings;
        }

        public double getEarnings() {

            return earnings;
        }

        public double getEarningsFactor() {

            return earningsFactor;
        }
    }
}
