package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;

import java.util.List;

public interface FormulasStatsDao extends Dao {

    void deleteFormulaStats(int drawId);

    List<Integer> getDrawIdsWithoutFormulasStats();

    List<FormulaStats> getFormulasStats();

    List<FormulaStats> getFormulasStats(int drawId);

    void insertFormulaStats(FormulaStats formulaStats);
}
