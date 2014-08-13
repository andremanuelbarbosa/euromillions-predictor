package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.SortedSet;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draws;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Result;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class ReverseIntervalAlgorithm extends Algorithm {

  public ReverseIntervalAlgorithm() {

    super(false);
  }

  private static Integer getMaximumIntervalItem(SortedSet<Integer> items, ItemType itemType) {

    int maximumInterval = -1;

    Integer maximumIntervalItem = 0;

    for (Integer item : items) {

      int itemInterval = 0;

      if (itemType == ItemType.NUMBER) {

        itemInterval = Draws.getNumbers().get(item - 1).getInterval();

      } else if (itemType == ItemType.STAR) {

        itemInterval = Draws.getStars().get(item - 1).getInterval();
      }

      if (itemInterval > maximumInterval) {

        maximumInterval = itemInterval;

        maximumIntervalItem = item;
      }
    }

    return maximumIntervalItem;
  }

  public int getMaximumIntervalFromNumbers(SortedSet<Integer> numbers) {

    int maximumInterval = -1;

    for (Integer number : numbers) {

      int numberInterval = Draws.getNumbers().get(number - 1).getInterval();

      if (numberInterval >= maximumInterval) {

        maximumInterval = numberInterval;
      }
    }

    return maximumInterval > -1 ? maximumInterval : Draws.getDraws().size();
  }

  public Integer getMaximumIntervalNumber(SortedSet<Integer> numbers) {

    return getMaximumIntervalItem(numbers, ItemType.NUMBER);
  }

  public int getMaximumIntervalFromStars(SortedSet<Integer> stars) {

    int maximumInterval = -1;

    for (Integer star : stars) {

      int starInterval = Draws.getStars().get(star - 1).getInterval();

      if (starInterval >= maximumInterval) {

        maximumInterval = starInterval;
      }
    }

    return maximumInterval > -1 ? maximumInterval : Draws.getDraws().size();
  }

  public Integer getMaximumIntervalStar(SortedSet<Integer> stars) {

    return getMaximumIntervalItem(stars, ItemType.STAR);
  }

  @Override
  public Bet getNextBet() {

    Bet bet = new Bet(this);

    for (Number number : Draws.getNumbers()) {

      if (number.getInterval() < getMaximumIntervalFromNumbers(bet.getNumbers())) {

        if (bet.getNumbers().size() >= Result.NUMBERS_COUNT) {

          bet.getNumbers().remove(getMaximumIntervalNumber(bet.getNumbers()));
        }

        bet.addNumber(number.getId());
      }
    }

    for (Star star : Draws.getStars()) {

      if (star.getInterval() < getMaximumIntervalFromStars(bet.getStars())) {

        if (bet.getStars().size() >= Result.STARS_COUNT) {

          bet.getStars().remove(getMaximumIntervalStar(bet.getStars()));
        }

        bet.addStar(star.getId());
      }
    }

    return bet;
  }
}
