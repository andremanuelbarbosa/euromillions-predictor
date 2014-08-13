package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.SortedSet;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draws;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Result;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class ReverseFreqAlgorithm extends Algorithm {

  public ReverseFreqAlgorithm() {

    super(false);
  }

  private static Integer getMaximumRelativeFreqItem(SortedSet<Integer> items, ItemType itemType) {

    double maximumRelativeFreq = 0.0;

    Integer maximumRelativeFreqItem = 0;

    for (Integer item : items) {

      double itemRelativeFreq = 0.0;

      if (itemType == ItemType.NUMBER) {

        itemRelativeFreq = Draws.getNumbers().get(item - 1).getRelativeFreq();

      } else if (itemType == ItemType.STAR) {

        itemRelativeFreq = Draws.getStars().get(item - 1).getRelativeFreq();
      }

      if (itemRelativeFreq > maximumRelativeFreq) {

        maximumRelativeFreq = itemRelativeFreq;

        maximumRelativeFreqItem = item;
      }
    }

    return maximumRelativeFreqItem;
  }

  public double getMaximumRelativeFreqFromNumbers(SortedSet<Integer> numbers) {

    double maximumRelativeFreq = 0.0;

    for (Integer number : numbers) {

      double numberMaximumRelativeFreq = Draws.getNumbers().get(number - 1).getRelativeFreq();

      if (numberMaximumRelativeFreq > maximumRelativeFreq) {

        maximumRelativeFreq = numberMaximumRelativeFreq;
      }
    }

    return maximumRelativeFreq > 0.0 ? maximumRelativeFreq : 1.0;
  }

  public Integer getMaximumRelativeFreqNumber(SortedSet<Integer> numbers) {

    return getMaximumRelativeFreqItem(numbers, ItemType.NUMBER);
  }

  public double getMaximumRelativeFreqFromStars(SortedSet<Integer> stars) {

    double maximumRelativeFreq = 0.0;

    for (Integer star : stars) {

      double starMaximumRelativeFreq = Draws.getStars().get(star - 1).getRelativeFreq();

      if (starMaximumRelativeFreq > maximumRelativeFreq) {

        maximumRelativeFreq = starMaximumRelativeFreq;
      }
    }

    return maximumRelativeFreq > 0.0 ? maximumRelativeFreq : 1.0;
  }

  public Integer getMaximumRelativeFreqStar(SortedSet<Integer> stars) {

    return getMaximumRelativeFreqItem(stars, ItemType.STAR);
  }

  @Override
  public Bet getNextBet() {

    Bet bet = new Bet(this);

    for (Number number : Draws.getNumbers()) {

      if (number.getRelativeFreq() < getMaximumRelativeFreqFromNumbers(bet.getNumbers())) {

        if (bet.getNumbers().size() >= Result.NUMBERS_COUNT) {

          bet.getNumbers().remove(getMaximumRelativeFreqNumber(bet.getNumbers()));
        }

        bet.addNumber(number.getId());
      }
    }

    for (Star star : Draws.getStars()) {

      if (star.getRelativeFreq() < getMaximumRelativeFreqFromStars(bet.getStars())) {

        if (bet.getStars().size() >= Result.STARS_COUNT) {

          bet.getStars().remove(getMaximumRelativeFreqStar(bet.getStars()));
        }

        bet.addStar(star.getId());
      }
    }

    return bet;
  }
}
