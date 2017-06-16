package com.andremanuelbarbosa.euromillions.predictor.domain;

public class DrawFormula {

    private final int drawId;
    private final String formulaName;
    private final int starsPoints;
    private final int numbersPoints;

    public DrawFormula(int drawId, String formulaName, int starsPoints, int numbersPoints) {

        this.drawId = drawId;
        this.formulaName = formulaName;
        this.starsPoints = starsPoints;
        this.numbersPoints = numbersPoints;
    }

    public int getDrawId() {

        return drawId;
    }

    public String getFormulaName() {

        return formulaName;
    }

    public int getStarsPoints() {

        return starsPoints;
    }

    public int getNumbersPoints() {

        return numbersPoints;
    }
}
