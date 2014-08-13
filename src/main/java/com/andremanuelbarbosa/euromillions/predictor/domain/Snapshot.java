package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.LinkedList;
import java.util.List;

public class Snapshot {

  private static final int DRAWS_COUNT_BEFORE_ELEVEN_STARS = 378;

  final List<Draw> draws;
  final List<Star> stars = new LinkedList<>();
  final List<Number> numbers = new LinkedList<>();

  public Snapshot(List<Draw> draws) {

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

    for (int i = 0; i < draws.size(); i++) {

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
  }

  public List<Draw> getDraws() {

    return draws;
  }

  public List<Star> getStars() {

    return stars;
  }

  public List<Number> getNumbers() {

    return numbers;
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
