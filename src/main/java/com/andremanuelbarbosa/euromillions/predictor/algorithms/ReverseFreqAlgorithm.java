package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draws;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Result;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class ReverseFreqAlgorithm extends Algorithm {

  public ReverseFreqAlgorithm() {

    super(false);
  }

  @Override
  public Bet getNextBet() {

    Bet bet = new Bet(this);

    double minimumRelativeFreqNumber = 1.0;

    for (Number number : Draws.getNumbers()) {

      if (number.getRelativeFreq() < minimumRelativeFreqNumber) {

        minimumRelativeFreqNumber = number.getRelativeFreq();

        if (bet.getNumbers().size() >= Result.NUMBERS_COUNT) {

          bet.getNumbers().remove(getMaximumRelativeFreqNumber(bet.getNumbers()));
        }

        bet.addNumber(number.getId());
      }
    }

    double minimumRelativeFreqStar = 1.0;

    for (Star star : Draws.getStars()) {

      if (star.getRelativeFreq() < minimumRelativeFreqStar) {

        minimumRelativeFreqStar = star.getRelativeFreq();

        if (bet.getStars().size() >= Result.STARS_COUNT) {

          bet.getStars().remove(getMaximumRelativeFreqStar(bet.getStars()));
        }

        bet.addStar(star.getId());
      }
    }

    return bet;
  }
}
