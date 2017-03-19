package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;

import java.util.List;
import java.util.SortedSet;

public interface BetsDao extends Dao {

    void deleteBet(long id);

    void deleteBetStars(long betId);

    void deleteBetNumbers(long betId);

    Bet getBet(long id);

    SortedSet<Integer> getBetStars(long betId);

    SortedSet<Integer> getBetNumbers(long betId);

    List<Bet> getBets();

    List<Bet> getBets(int drawId);

    void insertBet(long id, Bet bet);

    void insertBetStar(long betId, int star);

    void insertBetNumber(long betId, int number);

    void updateBet(Bet bet);
}
