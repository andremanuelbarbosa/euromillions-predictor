package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;

import java.util.List;

public interface FormulasStatsDao extends Dao {

    void deleteFormulaStats(int drawId);

    List<Integer> getDrawIdsWithoutFormulasStats();

    List<FormulaStats> getFormulasStats(int drawId, String formulaName);

    List<FormulaStats.Formula> getFormulasStatsFormulas(int minDrawId, int maxDrawId, int minWins, double minEarningsFactor, int count);

    void insertFormulaStats(FormulaStats formulaStats);
}
