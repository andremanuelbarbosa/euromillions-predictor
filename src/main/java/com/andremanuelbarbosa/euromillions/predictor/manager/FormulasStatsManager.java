package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.FormulasStatsDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.Formula;
import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.AVAILABLE_PROCESSORS;

@Singleton
public class FormulasStatsManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormulasStatsManager.class);

    private final DrawsManager drawsManager;
    private final FormulasManager formulasManager;

    private final FormulasStatsDao formulasStatsDao;

    @Inject
    public FormulasStatsManager(DrawsManager drawsManager, FormulasManager formulasManager, FormulasStatsDao formulasStatsDao) {

        this.drawsManager = drawsManager;
        this.formulasManager = formulasManager;

        this.formulasStatsDao = formulasStatsDao;
    }

    public void deleteFormulaStats(int drawId) {

        formulasStatsDao.deleteFormulaStats(drawId);
    }

    public List<Integer> getDrawIdsWithoutFormulasStats() {

        return formulasStatsDao.getDrawIdsWithoutFormulasStats();
    }

    private double getDoubleWithTwoDecimalPlaces(double rawDouble) {

        return Math.round(rawDouble * 100.0) / 100.0;
    }

    public List<FormulaStats> getFormulasStats(Integer drawId, String formulaName) {

        return formulasStatsDao.getFormulasStats(drawId != null ? drawId : -1, formulaName != null ? formulaName : "");
    }

    public List<FormulaStats.Formula> getFormulasStatsFormulas(Integer minDrawId, Integer maxDrawId, Double minEarningsFactor) {

        return formulasStatsDao.getFormulasStatsFormulas(minDrawId != null ? minDrawId : -1, maxDrawId != null ? maxDrawId : -1,
            minEarningsFactor != null ? minEarningsFactor : -1);
    }

    public void updateFormulasStats(int drawId) {

        new Thread() {

            public void run() {

                updateFormulasStats(drawId, Lists.reverse(drawsManager.getDraws(true, true, true)).subList(0, drawId), formulasManager.getFormulas());
            }

        }.start();
    }

    public void updateFormulasStats(int drawId, List<Draw> draws, List<Formula> formulas) {

        LOGGER.debug("Updating the Formulas Stats for Draw with ID [{}]...", drawId);

        deleteFormulaStats(drawId);

        final long startTime = System.currentTimeMillis();

        final ExecutorService executorServiceFormulas = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

        formulas.forEach(formula -> {

            executorServiceFormulas.execute(new FormulaThread(draws, drawId, formula));
        });

        executorServiceFormulas.shutdown();

        try {

            executorServiceFormulas.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }

        LOGGER.debug("The Formulas Stats for Draw with ID [{}] have been updated in [{}] ms.", drawId, (System.currentTimeMillis() - startTime));
    }

    class FormulaThread implements Runnable {

        private final List<Draw> draws;
        private final int drawId;
        private final Formula formula;

        public FormulaThread(List<Draw> draws, int drawId, Formula formula) {

            this.draws = draws;
            this.drawId = drawId;
            this.formula = formula;
        }

        @Override
        public void run() {

            final Draw draw = draws.get(drawId - 1);

            final Bet bet = formula.getNextBet(draws.subList(0, drawId - 1));

            final int starsPoints = bet.getStarsPoints(draw);
            final int numbersPoints = bet.getNumbersPoints(draw);
            final String points = "(" + numbersPoints + "+" + starsPoints + ") " + bet.getPoints(draw);

            final double costs = draw.getCost();

            double winnings = 0.0;

            if (bet.isWinner(draw)) {

                winnings = draw.getPrize(starsPoints, numbersPoints);
            }

            final double earnings = getDoubleWithTwoDecimalPlaces(winnings - costs);
            final double earningsFactor = getDoubleWithTwoDecimalPlaces(earnings / costs);

            formulasStatsDao.insertFormulaStats(new FormulaStats(drawId, formula.getName(), costs, points, winnings, earnings, earningsFactor));
        }
    }
}
