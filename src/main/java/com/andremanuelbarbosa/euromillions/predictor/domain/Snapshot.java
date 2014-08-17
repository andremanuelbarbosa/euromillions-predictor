package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.LinkedList;
import java.util.List;

public class Snapshot {

  private static final int DRAWS_COUNT_BEFORE_ELEVEN_STARS = 378;

  final List<? extends Draw> draws;
  final List<Star> stars = new LinkedList<>();
  final List<Number> numbers = new LinkedList<>();

  int starsMaximumInterval;
  int numbersMaximumInterval;

  public Snapshot(List<? extends Draw> draws) {

    this.draws = draws;

    loadStarsAndNumbers();
  }

  private int getFreq(int id, ItemType itemType) {

    int freq = 0;

    for (Draw draw : draws) {

      if (itemType == ItemType.STAR) {

        if (draw.getNumbers().contains(id)) {

          freq++;
        }

      } else if (itemType == ItemType.NUMBER) {

        if (draw.getNumbers().contains(id)) {

          freq++;
        }
      }
    }

    return freq;
  }

  private int getInterval(int id, ItemType itemType) {

    int interval = 0;

    for (int i = (draws.size() - 1); i >= 0; i--) {

      if ((itemType == ItemType.STAR && draws.get(i).getStars().contains(id))
          || (itemType == ItemType.NUMBER && draws.get(i).getNumbers().contains(id))) {

        break;

      } else {

        interval++;
      }
    }

    return interval;
  }

  private void loadStarsAndNumbers() {

    for (int i = 1; i <= Star.COUNT; i++) {

      stars.add(new Star(i, getInterval(i, ItemType.STAR), i < 10 ? (double) getFreq(i, ItemType.STAR) / draws.size()
          : (double) getFreq(i, ItemType.STAR) / DRAWS_COUNT_BEFORE_ELEVEN_STARS));
    }

    for (int i = 1; i <= Number.COUNT; i++) {

      numbers.add(new Number(i, getInterval(i, ItemType.NUMBER), (double) getFreq(i, ItemType.NUMBER) / draws.size()));
    }

    starsMaximumInterval = getItemMaximumInterval(ItemType.STAR);
    numbersMaximumInterval = getItemMaximumInterval(ItemType.NUMBER);
  }

  public List<? extends Draw> getDraws() {

    return draws;
  }

  public List<Star> getStars() {

    return stars;
  }

  public List<Number> getNumbers() {

    return numbers;
  }

  public Draw getLastDraw() {

    return draws.get(draws.size() - 1);
  }

  private int getItemMaximumInterval(ItemType itemType) {

    int itemMaximumInterval = 0;

    if (itemType == ItemType.STAR) {

      for (Star star : stars) {

        if (star.getInterval() > itemMaximumInterval) {

          itemMaximumInterval = star.getInterval();
        }
      }

    } else if (itemType == ItemType.NUMBER) {

      for (Number mumber : numbers) {

        if (mumber.getInterval() > itemMaximumInterval) {

          itemMaximumInterval = mumber.getInterval();
        }
      }
    }

    return itemMaximumInterval;
  }

  public int getStarsMaximumInterval() {

    return starsMaximumInterval;
  }

  public int getNumbersMaximumInterval() {

    return numbersMaximumInterval;
  }

  public void showStatistics() {

    System.out.println("  STAR | INTERVAL | RELATIVE FREQ");
    System.out.println("---------------------------------");

    for (Star star : stars) {

      System.out.println(star.getStatisticsLine());
    }

    System.out.println("");
    System.out.println("NUMBER | INTERVAL | RELATIVE FREQ");
    System.out.println("---------------------------------");

    for (Number number : numbers) {

      System.out.println(number.getStatisticsLine());
    }
  }
}
