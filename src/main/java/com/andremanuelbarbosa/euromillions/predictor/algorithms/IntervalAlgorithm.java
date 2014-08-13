package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.SortedSet;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Result;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class IntervalAlgorithm extends Algorithm {

  public IntervalAlgorithm(Snapshot snapshot) {

    super(snapshot, false);
  }

  private Integer getMinimumIntervalItem(SortedSet<Integer> items, ItemType itemType) {

    int minimumInterval = snapshot.getDraws().size();

    Integer minimumIntervalItem = 0;

    for (Integer item : items) {

      int itemInterval = 0;

      if (itemType == ItemType.STAR) {

        itemInterval = snapshot.getStars().get(item - 1).getInterval();

      } else if (itemType == ItemType.NUMBER) {

        itemInterval = snapshot.getNumbers().get(item - 1).getInterval();
      }

      if (itemInterval < minimumInterval) {

        minimumInterval = itemInterval;

        minimumIntervalItem = item;
      }
    }

    return minimumIntervalItem;
  }

  public int getMinimumIntervalFromNumbers(SortedSet<Integer> numbers) {

    int minimumInterval = snapshot.getDraws().size();

    for (Integer number : numbers) {

      int numberMinimumInterval = snapshot.getNumbers().get(number - 1).getInterval();

      if (numberMinimumInterval < minimumInterval) {

        minimumInterval = numberMinimumInterval;
      }
    }

    return minimumInterval < snapshot.getDraws().size() ? minimumInterval : 0;
  }

  public Integer getMinimumIntervalNumber(SortedSet<Integer> numbers) {

    return getMinimumIntervalItem(numbers, ItemType.NUMBER);
  }

  public int getMinimumIntervalFromStars(SortedSet<Integer> stars) {

    int minimumInterval = snapshot.getDraws().size();

    for (Integer star : stars) {

      int starMinimumInterval = snapshot.getNumbers().get(star - 1).getInterval();

      if (starMinimumInterval < minimumInterval) {

        minimumInterval = starMinimumInterval;
      }
    }

    return minimumInterval < snapshot.getDraws().size() ? minimumInterval : 0;
  }

  public Integer getMinimumIntervalStar(SortedSet<Integer> stars) {

    return getMinimumIntervalItem(stars, ItemType.STAR);
  }

  @Override
  public Bet getNextBet() {

    Bet bet = new Bet(this);

    for (Star star : snapshot.getStars()) {

      if (star.getInterval() > getMinimumIntervalFromStars(bet.getStars())) {

        if (bet.getStars().size() >= Result.STARS_COUNT) {

          bet.getStars().remove(getMinimumIntervalStar(bet.getStars()));
        }

        bet.addStar(star.getId());
      }
    }

    for (Number number : snapshot.getNumbers()) {

      if (number.getInterval() > getMinimumIntervalFromNumbers(bet.getNumbers())) {

        if (bet.getNumbers().size() >= Result.NUMBERS_COUNT) {

          bet.getNumbers().remove(getMinimumIntervalNumber(bet.getNumbers()));
        }

        bet.addNumber(number.getId());
      }
    }

    return bet;
  }
}
