package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.FormulasStatsDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Formula;
import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import com.google.common.util.concurrent.AtomicDouble;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.AVAILABLE_PROCESSORS;

@Singleton
public class FormulasStatsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormulasStatsManager.class);

//    private static final DecimalFormat DECIMAL_FORMAT_TWO_FRACTIONAL_DIGITS = new DecimalFormat("0.00");

    final ConcurrentHashMap<Formula, Integer> formulasPointsSum = new ConcurrentHashMap<>();
    final ConcurrentHashMap<Formula, String> formulasMaximumPoints = new ConcurrentHashMap<>();

    final ConcurrentHashMap<Formula, Integer> formulasWins = new ConcurrentHashMap<>();
    final ConcurrentHashMap<Formula, Double> formulasEarnings = new ConcurrentHashMap<>();
    final ConcurrentHashMap<Formula, ConcurrentHashMap<Integer, Integer>> formulasPoints = new ConcurrentHashMap<>();
    final ConcurrentHashMap<Formula, ConcurrentHashMap<Integer, Integer>> formulasStarsDistributedFreq = new ConcurrentHashMap<>();
    final ConcurrentHashMap<Formula, ConcurrentHashMap<Integer, Integer>> formulasNumbersDistributedFreq = new ConcurrentHashMap<>();

    private final FormulasStatsDao formulasStatsDao;

    @Inject
    public FormulasStatsManager(FormulasStatsDao formulasStatsDao) {

        this.formulasStatsDao = formulasStatsDao;
    }

    public void deleteFormulaStats(int drawId) {

        formulasStatsDao.deleteFormulaStatsFormulaNumbers(drawId);
        formulasStatsDao.deleteFormulaStatsFormulaStars(drawId);
        formulasStatsDao.deleteFormulaStatsFormulas(drawId);
        formulasStatsDao.deleteFormulaStats(drawId);
    }

    public void deleteFormulaStats(int drawId, int drawsCount) {

        formulasStatsDao.deleteFormulaStatsFormulaNumbers(drawId, drawsCount);
        formulasStatsDao.deleteFormulaStatsFormulaStars(drawId, drawsCount);
        formulasStatsDao.deleteFormulaStatsFormulas(drawId, drawsCount);
        formulasStatsDao.deleteFormulaStats(drawId, drawsCount);
    }

    public List<Integer> getDrawIdsWithoutFormulasStats() {

        return formulasStatsDao.getDrawIdsWithoutFormulasStats();
    }

    private double getDoubleWithTwoDecimalPlaces(double rawDouble) {

        return Math.round(rawDouble * 100.0) / 100.0;
    }

    public FormulaStats getFormulaStats(int drawId, int drawsCount) {

        return loadFormulaStats(formulasStatsDao.getFormulaStats(drawId, drawsCount));
    }

    public List<FormulaStats> getFormulasStats() {

        return loadFormulasStats(formulasStatsDao.getFormulasStats());
    }

    public List<FormulaStats> getFormulasStats(int drawId) {

        return loadFormulasStats(formulasStatsDao.getFormulasStats(drawId));
    }

    private List<FormulaStats> loadFormulasStats(List<FormulaStats> formulasStats) {

        formulasStats.forEach(formulaStats -> {

            loadFormulaStats(formulaStats);
        });

        return formulasStats;
    }

    private FormulaStats loadFormulaStats(FormulaStats formulaStats) {

        formulaStats.getMaximumPointsFormulas().addAll(formulasStatsDao.getFormulaNamesWithMaximumPoints(formulaStats.getDrawId(), formulaStats.getDrawsCount(), formulaStats.getMaximumPoints()));
        formulaStats.getMaximumWinsFormulas().addAll(formulasStatsDao.getFormulaNamesWithWins(formulaStats.getDrawId(), formulaStats.getDrawsCount(), formulaStats.getMaximumWins()));
        formulaStats.getMaximumEarningsFormulas().addAll(formulasStatsDao.getFormulaNamesWithEarnings(formulaStats.getDrawId(), formulaStats.getDrawsCount(), formulaStats.getMaximumEarnings()));

        return formulaStats;
    }

    public void updateFormulasStats(int drawId, List<Draw> draws, List<Formula> formulas) {

        updateFormulasStats(drawId, 5, draws, formulas);
        updateFormulasStats(drawId, 10, draws, formulas);
        updateFormulasStats(drawId, 15, draws, formulas);
        updateFormulasStats(drawId, 20, draws, formulas);
        updateFormulasStats(drawId, 25, draws, formulas);
    }

    public void updateFormulasStats(int drawId, int drawsCount, List<Draw> draws, List<Formula> formulas) {

        deleteFormulaStats(drawId, drawsCount);

        final int startingDrawId = drawId - drawsCount;
        final int finishingDrawId = drawId - 1;

        final FormulaStats formulaStats = new FormulaStats(drawId, drawsCount, startingDrawId, finishingDrawId);

        formulasStatsDao.insertFormulaStats(formulaStats);

        final long startTime = System.currentTimeMillis();

        final ExecutorService executorServiceFormulas = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

        formulas.forEach(formula -> {

            formulasPoints.put(formula, new ConcurrentHashMap<>());
            formulasStarsDistributedFreq.put(formula, new ConcurrentHashMap<>());
            formulasNumbersDistributedFreq.put(formula, new ConcurrentHashMap<>());

            formulasPointsSum.put(formula, 0);
            formulasWins.put(formula, 0);
            formulasEarnings.put(formula, 0.0);
            formulasMaximumPoints.put(formula, "(0+0) 0");

            executorServiceFormulas.execute(new FormulaThread(formula, draws, startingDrawId, finishingDrawId, formulaStats));
        });

        executorServiceFormulas.shutdown();

        try {

            executorServiceFormulas.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        final AtomicDouble formulasCosts = new AtomicDouble(0.0);

        for (int i = startingDrawId; i <= finishingDrawId; i++) {

            formulasCosts.getAndAdd(draws.get(i).getCost());
        }

        final long executionTime = System.currentTimeMillis() - startTime;

        final int maximumWins = formulasStatsDao.getMaximumWins(drawId, drawsCount);
        final String maximumPoints = formulasStatsDao.getMaximumPoints(drawId, drawsCount);
        final double maximumEarnings = formulasStatsDao.getMaximumEarnings(drawId, drawsCount);

        formulaStats.setCosts(formulasCosts.doubleValue());
        formulaStats.setExecutionTime(executionTime);
        formulaStats.setMaximumPoints(maximumPoints);
        formulaStats.setMaximumWins(maximumWins);
        formulaStats.setMaximumWinsPercentage(getDoubleWithTwoDecimalPlaces((double) maximumWins / drawsCount * 100));
        formulaStats.setMaximumEarnings(getDoubleWithTwoDecimalPlaces(maximumEarnings));
        formulaStats.setMaximumEarningsPercentage(getDoubleWithTwoDecimalPlaces(maximumEarnings / formulasCosts.doubleValue() * 100));

        formulasStatsDao.updateFormulaStats(formulaStats);
    }

    class FormulaThread implements Runnable {

        private final ConcurrentHashMap<Integer, Integer> formulaPoints = new ConcurrentHashMap<>();
        private final ConcurrentHashMap<Integer, Integer> formulaStarsDistributedFreq = new ConcurrentHashMap<>();
        private final ConcurrentHashMap<Integer, Integer> formulaNumbersDistributedFreq = new ConcurrentHashMap<>();

        private final Formula formula;
        private final List<Draw> draws;
        private final int startingDrawId;
        private final int finishingDrawId;
        private final FormulaStats formulaStats;

        private int formulaWins = 0;
        private double formulaCosts = 0.0;
        private double formulaEarnings = 0.0;
        private String formulaMaximumPoints = "(0+0) 0";

        public FormulaThread(Formula formula, List<Draw> draws, int startingDrawId, int finishingDrawId, FormulaStats formulaStats) {

            this.formula = formula;
            this.draws = draws;
            this.startingDrawId = startingDrawId;
            this.finishingDrawId = finishingDrawId;
            this.formulaStats = formulaStats;
        }

        @Override
        public void run() {

//            LOGGER.debug("The Stats for Formula [{}] with starting Draw ID [{}] and finishing Draw ID [{}] are being calculated...", formula.getName(), startingDrawId, finishingDrawId);

            for (int i = startingDrawId; i <= finishingDrawId; i++) {

                final Draw nextDraw = draws.get(i);
                final Bet bet = formula.getNextBet(draws.subList(0, i));

                final int points = bet.getPoints(nextDraw);
                final int starsPoints = bet.getStarsPoints(nextDraw);
                final int numbersPoints = bet.getNumbersPoints(nextDraw);

                final String combinedPoints = "(" + numbersPoints + "+" + starsPoints + ") " + points;

                formulaPoints.put(points, formulaPoints.getOrDefault(points, 0) + 1);
                formulaStarsDistributedFreq.put(starsPoints, formulaStarsDistributedFreq.getOrDefault(starsPoints, 0) + 1);
                formulaNumbersDistributedFreq.put(numbersPoints, formulaNumbersDistributedFreq.getOrDefault(numbersPoints, 0) + 1);

                formulaCosts += nextDraw.getCost();

                if (bet.isWinner(nextDraw)) {

                    formulaWins++;
                    formulaEarnings += nextDraw.getPrize(starsPoints, numbersPoints);

                    if (combinedPoints.compareTo(formulaMaximumPoints) > 0) {

                        formulaMaximumPoints = combinedPoints;
                    }
                }
            }

            formulasStatsDao.insertFormulaStatsFormula(formulaStats.getDrawId(), formulaStats.getDrawsCount(), new FormulaStats.Formula(formula.getName(), formulaMaximumPoints,
                formulaWins, getDoubleWithTwoDecimalPlaces((double) formulaWins / formulaStats.getDrawsCount() * 100),
                getDoubleWithTwoDecimalPlaces(formulaEarnings), getDoubleWithTwoDecimalPlaces(formulaEarnings / formulaCosts * 100),
                getDoubleWithTwoDecimalPlaces(formulaEarnings - formulaCosts)));

            formulaStarsDistributedFreq.forEach((star, freq) -> {

                formulasStatsDao.insertFormulaStatsFormulaStars(formulaStats.getDrawId(), formulaStats.getDrawsCount(), formula.getName(), star, freq);
            });

            formulaNumbersDistributedFreq.forEach((number, freq) -> {

                formulasStatsDao.insertFormulaStatsFormulaNumbers(formulaStats.getDrawId(), formulaStats.getDrawsCount(), formula.getName(), number, freq);
            });

//            LOGGER.debug("The Stats for Formula [{}] with starting Draw ID [{}] and finishing Draw ID [{}] have been calculated.", formula.getName(), startingDrawId, finishingDrawId);
        }
    }
}
