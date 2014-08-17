package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Result;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class RandomAlgorithm extends Algorithm {

  public RandomAlgorithm(Snapshot snapshot) {

    super(snapshot);
  }

  @Override
  double getItemWeight(Item item) {

    return (double) 1 / (item.getItemType() == ItemType.STAR ? Star.COUNT : Number.COUNT);
  }

  @Override
  public Bet getNextBet() {

    Bet bet = new Bet(this);

    for (int i = 0; i < Result.STARS_COUNT; i++) {

      Integer star = RANDOM.nextInt(Star.COUNT) + 1;

      while (bet.getStars().contains(star)) {

        star = RANDOM.nextInt(Star.COUNT) + 1;
      }

      bet.getStars().add(star);
    }

    for (int i = 0; i < Result.NUMBERS_COUNT; i++) {

      Integer number = RANDOM.nextInt(Number.COUNT) + 1;

      while (bet.getNumbers().contains(number)) {

        number = RANDOM.nextInt(Number.COUNT) + 1;
      }

      bet.getNumbers().add(number);
    }

    return bet;
  }
}
