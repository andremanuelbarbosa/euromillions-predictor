package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.BetStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AtomicDouble;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class BetsStatsManager {

    private final DrawsManager drawsManager;

    @Inject
    public BetsStatsManager(DrawsManager drawsManager) {

        this.drawsManager = drawsManager;
    }

    public BetStats getBetStats(Bet bet) {

        return getBetStats(Lists.newArrayList(bet));
    }

    public BetStats getBetStats(List<Bet> bets) {

        final AtomicDouble costs = new AtomicDouble(0.0);
        final AtomicDouble earnings = new AtomicDouble(0.0);

        bets.forEach(bet -> {

            final Draw draw = drawsManager.getDraw(bet.getDrawId());

            costs.addAndGet(draw.getCost());
            earnings.addAndGet(draw.getPrize(bet.getStarsPoints(draw), bet.getNumbersPoints(draw)));
        });

        return new BetStats(costs.doubleValue(), earnings.doubleValue(), earnings.doubleValue() - costs.doubleValue());
    }
}
