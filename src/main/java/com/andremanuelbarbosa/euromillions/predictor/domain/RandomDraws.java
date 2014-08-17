package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomDraws {

  private static final Random RANDOM = new Random();

  private final List<RandomDraw> randomDraws = new LinkedList<>();

  public RandomDraws(int numRandomDraw) {

    loadDraws(numRandomDraw);
  }

  private void loadDraws(int numRandomDraw) {

    for (int i = 0; i < numRandomDraw; i++) {

      RandomDraw randomDraw = new RandomDraw();

      for (int j = 0; j < Result.STARS_COUNT; j++) {

        Integer star = RANDOM.nextInt(Star.COUNT) + 1;

        while (randomDraw.getStars().contains(star)) {

          star = RANDOM.nextInt(Star.COUNT) + 1;
        }

        randomDraw.getStars().add(star);
      }

      for (int j = 0; j < Result.NUMBERS_COUNT; j++) {

        Integer number = RANDOM.nextInt(Number.COUNT) + 1;

        while (randomDraw.getNumbers().contains(number)) {

          number = RANDOM.nextInt(Number.COUNT) + 1;
        }

        randomDraw.getNumbers().add(number);
      }

      randomDraws.add(randomDraw);
    }
  }

  public List<RandomDraw> getRandomDraws() {

    return randomDraws;
  }
}
