package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.BetsDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class BetsManager {

    private final BetsDao betsDao;
    private final IdsManager idsManager;

    @Inject
    public BetsManager(BetsDao betsDao, IdsManager idsManager) {

        this.betsDao = betsDao;
        this.idsManager = idsManager;
    }

    public void deleteBet(long id) {

        betsDao.deleteBetStars(id);
        betsDao.deleteBetNumbers(id);

        betsDao.deleteBet(id);
    }

    public Bet getBet(long id) {

        final Bet bet = betsDao.getBet(id);

        loadBet(bet);

        return bet;
    }

    public List<Bet> getBets() {

        return loadBets(betsDao.getBets());
    }

    public List<Bet> getBets(int drawId) {

        return loadBets(betsDao.getBets(drawId));
    }

    private List<Bet> loadBets(List<Bet> bets) {

        bets.forEach(bet -> {

            loadBet(bet);
        });

        return bets;
    }

    private void loadBet(Bet bet) {

        bet.getStars().addAll(betsDao.getBetStars(bet.getId()));
        bet.getNumbers().addAll(betsDao.getBetNumbers(bet.getId()));
    }

    public Bet saveBet(Bet bet) {

        final long id = bet.getId() != null ? bet.getId() : idsManager.getNextId(Bet.class);

        if (bet.getId() == null) {

            betsDao.insertBet(id, bet);

        } else {

            betsDao.deleteBetStars(id);
            betsDao.deleteBetNumbers(id);

            betsDao.updateBet(bet);
        }

        bet.getStars().forEach(star -> {

            betsDao.insertBetStar(id, star);
        });

        bet.getNumbers().forEach(number -> {

            betsDao.insertBetNumber(id, number);
        });

        return getBet(id);
    }
}
