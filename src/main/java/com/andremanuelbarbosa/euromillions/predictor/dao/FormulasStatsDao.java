package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;

import java.util.List;

public interface FormulasStatsDao extends Dao {

    void deleteFormulaStats(int drawId);

    void deleteFormulaStats(int drawId, int drawsCount);

    void deleteFormulaStatsFormulas(int drawId);

    void deleteFormulaStatsFormulas(int drawId, int drawsCount);

    void deleteFormulaStatsFormulaStars(int drawId);

    void deleteFormulaStatsFormulaStars(int drawId, int drawsCount);

    void deleteFormulaStatsFormulaStars(int drawId, int drawsCount, String formulaName);

    void deleteFormulaStatsFormulaNumbers(int drawId);

    void deleteFormulaStatsFormulaNumbers(int drawId, int drawsCount);

    void deleteFormulaStatsFormulaNumbers(int drawId, int drawsCount, String formulaName);

    List<Integer> getDrawIdsWithoutFormulasStats();

    List<String> getFormulaNamesWithEarnings(int drawId, int drawsCount, double earnings);

    List<String> getFormulaNamesWithMaximumPoints(int drawId, int drawsCount, String maximumPoints);

    List<String> getFormulaNamesWithWins(int drawId, int drawsCount, int wins);

    FormulaStats getFormulaStats(int drawId, int drawsCount);

    List<FormulaStats> getFormulasStats();

    List<FormulaStats> getFormulasStats(int drawId);

    double getMaximumEarnings(int drawId, int drawsCount);

    String getMaximumPoints(int drawId, int drawsCount);

    int getMaximumWins(int drawId, int drawsCount);

    void insertFormulaStats(FormulaStats formulaStats);

    void insertFormulaStatsFormula(int drawId, int drawsCount, FormulaStats.Formula formulaStatsFormula);

    void insertFormulaStatsFormulaStars(int drawId, int drawsCount, String formulaName, int stars, int freq);

    void insertFormulaStatsFormulaNumbers(int drawId, int drawsCount, String formulaName, int numbers, int freq);

    void updateFormulaStats(FormulaStats formulaStats);
}
