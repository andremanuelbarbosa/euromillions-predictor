package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.Random;
import java.util.SortedSet;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Item;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Result;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public abstract class Algorithm {

  static final Random RANDOM = new Random();

  final Snapshot snapshot;

  private final boolean randomized;

  public Algorithm(Snapshot snapshot, boolean randomized) {

    this.snapshot = snapshot;

    this.randomized = randomized;
  }

  public boolean isRandomized() {

    return randomized;
  }

  abstract double getItemWeight(Item item);

  private double getMinimumWeightFromStars(SortedSet<Integer> stars) {

    double starsMinimumWeight = 1.0;

    for (Integer star : stars) {

      double starWeight = getItemWeight(snapshot.getStars().get(star - 1));

      if (starWeight < starsMinimumWeight) {

        starsMinimumWeight = starWeight;
      }
    }

    return starsMinimumWeight;
  }

  private double getMinimumWeightFromNumbers(SortedSet<Integer> numbers) {

    double numbersMinimumWeight = 1.0;

    for (Integer number : numbers) {

      double numberWeight = getItemWeight(snapshot.getNumbers().get(number - 1));

      if (numberWeight < numbersMinimumWeight) {

        numbersMinimumWeight = numberWeight;
      }
    }

    return numbersMinimumWeight;
  }

  double getMinimumWeight(SortedSet<Integer> items, ItemType itemType) {

    double minimumWeight = itemType == ItemType.STAR ? getMinimumWeightFromStars(items)
        : getMinimumWeightFromNumbers(items);

    return minimumWeight < 1.0 ? minimumWeight : 0.0;
  }

  Integer getMinimumWeightItem(SortedSet<Integer> items, ItemType itemType) {

    double minimumWeight = getMinimumWeight(items, itemType);

    for (Integer item : items) {

      if (minimumWeight == 0.0) {

        return item;
      }

      if (itemType == ItemType.STAR) {

        if (getItemWeight(snapshot.getStars().get(item - 1)) == minimumWeight) {

          return item;
        }
      }

      if (itemType == ItemType.NUMBER) {

        if (getItemWeight(snapshot.getNumbers().get(item - 1)) == minimumWeight) {

          return item;
        }
      }
    }

    throw new IllegalStateException("Unable to find the Minimum Weight Item in " + items.toString());
  }

  public Bet getNextBet() {

    Bet bet = new Bet(this);

    for (Star star : snapshot.getStars()) {

      if (bet.getStars().size() < Result.STARS_COUNT) {

        bet.addStar(star.getId());

      } else if (getItemWeight(star) > getMinimumWeight(bet.getStars(), ItemType.STAR)) {

        if (bet.getStars().size() >= Result.STARS_COUNT) {

          bet.getStars().remove(getMinimumWeightItem(bet.getStars(), ItemType.STAR));
        }

        bet.addStar(star.getId());
      }
    }

    for (Number number : snapshot.getNumbers()) {

      if (bet.getNumbers().size() < Result.NUMBERS_COUNT) {

        bet.addNumber(number.getId());

      } else if (getItemWeight(number) > getMinimumWeight(bet.getNumbers(), ItemType.NUMBER)) {

        if (bet.getNumbers().size() >= Result.NUMBERS_COUNT) {

          bet.getNumbers().remove(getMinimumWeightItem(bet.getNumbers(), ItemType.NUMBER));
        }

        bet.addNumber(number.getId());
      }
    }

    return bet;
  }
}
