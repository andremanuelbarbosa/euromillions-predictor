package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.google.common.collect.Lists;

import java.util.List;

public class FormulaStats {

    private final int drawId;
    private final int drawsCount;
    private final int startingDrawId;
    private final int finishingDrawId;

    private Double costs;
    private Long executionTime;
    private String maximumPoints;
    private Integer maximumWins;
    private Double maximumWinsPercentage;
    private Double maximumEarnings;
    private Double maximumEarningsPercentage;

    private List<String> maximumPointsFormulas = Lists.newLinkedList();
    private List<String> maximumWinsFormulas = Lists.newLinkedList();
    private List<String> maximumEarningsFormulas = Lists.newLinkedList();

    public FormulaStats(int drawId, int drawsCount, int startingDrawId, int finishingDrawId) {

        this(drawId, drawsCount, startingDrawId, finishingDrawId, null, null, null, null, null, null, null);
    }

    public FormulaStats(int drawId, int drawsCount, int startingDrawId, int finishingDrawId, Double costs, Long executionTime, String maximumPoints, Integer maximumWins, Double maximumWinsPercentage, Double maximumEarnings, Double maximumEarningsPercentage) {

        this.drawId = drawId;
        this.drawsCount = drawsCount;
        this.startingDrawId = startingDrawId;
        this.finishingDrawId = finishingDrawId;
        this.costs = costs;
        this.executionTime = executionTime;
        this.maximumPoints = maximumPoints;
        this.maximumWins = maximumWins;
        this.maximumWinsPercentage = maximumWinsPercentage;
        this.maximumEarnings = maximumEarnings;
        this.maximumEarningsPercentage = maximumEarningsPercentage;
    }

    public int getDrawId() {

        return drawId;
    }

    public int getDrawsCount() {

        return drawsCount;
    }

    public int getStartingDrawId() {

        return startingDrawId;
    }

    public int getFinishingDrawId() {

        return finishingDrawId;
    }

    public Double getCosts() {

        return costs;
    }

    public void setCosts(Double costs) {

        this.costs = costs;
    }

    public Long getExecutionTime() {

        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {

        this.executionTime = executionTime;
    }

    public String getMaximumPoints() {

        return maximumPoints;
    }

    public void setMaximumPoints(String maximumPoints) {

        this.maximumPoints = maximumPoints;
    }

    public Integer getMaximumWins() {

        return maximumWins;
    }

    public void setMaximumWins(Integer maximumWins) {

        this.maximumWins = maximumWins;
    }

    public Double getMaximumWinsPercentage() {

        return maximumWinsPercentage;
    }

    public void setMaximumWinsPercentage(Double maximumWinsPercentage) {

        this.maximumWinsPercentage = maximumWinsPercentage;
    }

    public Double getMaximumEarnings() {

        return maximumEarnings;
    }

    public void setMaximumEarnings(Double maximumEarnings) {

        this.maximumEarnings = maximumEarnings;
    }

    public Double getMaximumEarningsPercentage() {

        return maximumEarningsPercentage;
    }

    public void setMaximumEarningsPercentage(Double maximumEarningsPercentage) {

        this.maximumEarningsPercentage = maximumEarningsPercentage;
    }

    public List<String> getMaximumPointsFormulas() {

        return maximumPointsFormulas;
    }

    public List<String> getMaximumWinsFormulas() {

        return maximumWinsFormulas;
    }

    public List<String> getMaximumEarningsFormulas() {

        return maximumEarningsFormulas;
    }

    public static class Formula {

        private final String formulaName;
        private final String maximumPoints;
        private final int wins;
        private final double winsPercentage;
        private final double earnings;
        private final double earningsPercentage;
        private final double balance;

        public Formula(String formulaName, String maximumPoints, int wins, double winsPercentage, double earnings, double earningsPercentage, double balance) {

            this.formulaName = formulaName;
            this.maximumPoints = maximumPoints;
            this.wins = wins;
            this.winsPercentage = winsPercentage;
            this.earnings = earnings;
            this.earningsPercentage = earningsPercentage;
            this.balance = balance;
        }

        public String getFormulaName() {

            return formulaName;
        }

        public String getMaximumPoints() {

            return maximumPoints;
        }

        public int getWins() {

            return wins;
        }

        public double getWinsPercentage() {

            return winsPercentage;
        }

        public double getEarnings() {

            return earnings;
        }

        public double getEarningsPercentage() {

            return earningsPercentage;
        }

        public double getBalance() {

            return balance;
        }
    }
}
