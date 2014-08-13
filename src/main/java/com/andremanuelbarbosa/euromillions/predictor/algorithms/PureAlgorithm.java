package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Result;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;

public class PureAlgorithm extends Algorithm {

  public PureAlgorithm(Snapshot snapshot) {

    super(snapshot, true);
  }

  @Override
  public Bet getNextBet() {

    Bet bet = new Bet(this);

    for (int i = 0; i < Result.STARS_COUNT; i++) {

      int starIndex = -1;

      do {

        starIndex = RANDOM.nextInt(11);

      } while (bet.getStars().contains(snapshot.getStars().get(starIndex).getId()));

      bet.addStar(snapshot.getStars().get(starIndex).getId());
    }

    for (int i = 0; i < Result.NUMBERS_COUNT; i++) {

      int numberIndex = -1;

      do {

        numberIndex = RANDOM.nextInt(Number.COUNT);

      } while (bet.getNumbers().contains(snapshot.getNumbers().get(numberIndex).getId()));

      bet.addNumber(snapshot.getNumbers().get(numberIndex).getId());
    }

    return bet;
  }
}
