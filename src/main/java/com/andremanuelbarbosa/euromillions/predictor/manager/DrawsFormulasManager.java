package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsFormulasDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawFormula;
import com.andremanuelbarbosa.euromillions.predictor.domain.Formula;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.AVAILABLE_PROCESSORS;

@Singleton
public class DrawsFormulasManager {

    private final DrawsFormulasDao drawsFormulasDao;

    @Inject
    public DrawsFormulasManager(DrawsFormulasDao drawsFormulasDao) {

        this.drawsFormulasDao = drawsFormulasDao;
    }

    public void deleteDrawFormulas(int drawId) {

        drawsFormulasDao.deleteDrawFormulas(drawId);
    }

    public DrawFormula getDrawFormula(int drawId, String formulaName) {

        return drawsFormulasDao.getDrawFormula(drawId, formulaName);
    }

    public List<DrawFormula> getDrawFormulas(int drawId) {

        return drawsFormulasDao.getDrawFormulas(drawId);
    }

    public void updateDrawFormulas(int drawId, List<Draw> draws, List<Formula> formulas) {

        final ExecutorService executorServiceFormulas = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

        formulas.forEach(formula -> {

            executorServiceFormulas.execute(new DrawFormulaThread(drawId, draws, formula));
        });

        executorServiceFormulas.shutdown();

        try {

            executorServiceFormulas.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }
    }

    class DrawFormulaThread implements Runnable {

        private final int drawId;
        private final List<Draw> draws;
        private final Formula formula;

        public DrawFormulaThread(int drawId, List<Draw> draws, Formula formula) {

            this.drawId = drawId;
            this.draws = draws;
            this.formula = formula;
        }

        @Override
        public void run() {

            final Draw draw = draws.get(drawId - 1);
            final Bet bet = formula.getNextBet(draws.subList(0, drawId - 1));

            final int starsPoints = bet.getStarsPoints(draw);
            final int numbersPoints = bet.getNumbersPoints(draw);

            drawsFormulasDao.insertDrawFormula(new DrawFormula(drawId, formula.getName(), starsPoints, numbersPoints));
        }
    }
}
