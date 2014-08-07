package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draws;

public class PureAlgorithm extends Algorithm {

  public PureAlgorithm() {

    super(true);
  }

  @Override
  public Bet getNextBet() {

    Bet bet = new Bet(this);

    for (int i = 0; i < 5; i++) {

      int numberIndex = -1;

      do {

        numberIndex = RANDOM.nextInt(50);

      } while (bet.getNumbers().contains(Draws.getNumbers().get(numberIndex).getId()));

      bet.addNumber(Draws.getNumbers().get(numberIndex).getId());
    }

    for (int i = 0; i < 2; i++) {

      int starIndex = -1;

      do {

        starIndex = RANDOM.nextInt(11);

      } while (bet.getStars().contains(Draws.getStars().get(starIndex).getId()));

      bet.addStar(Draws.getStars().get(starIndex).getId());
    }

    return bet;
  }
}
