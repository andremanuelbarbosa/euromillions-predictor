package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.Random;
import java.util.SortedSet;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draws;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;

public abstract class Algorithm {

  static final Random RANDOM = new Random();

  private final boolean randomized;

  public Algorithm(boolean randomized) {

    this.randomized = randomized;
  }

  public boolean isRandomized() {

    return randomized;
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

  static Integer getMaximumRelativeFreqNumber(SortedSet<Integer> numbers) {

    return getMaximumRelativeFreqItem(numbers, ItemType.NUMBER);
  }

  static Integer getMaximumRelativeFreqStar(SortedSet<Integer> stars) {

    return getMaximumRelativeFreqItem(stars, ItemType.STAR);
  }

  public abstract Bet getNextBet();
}
