package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;

public class Bet extends Result {

  private final Algorithm algorithm;

  public Bet(Algorithm algorithm) {

    this.algorithm = algorithm;
  }

  public void addStar(Integer star) {

    if (stars.size() >= STARS_COUNT) {

      throw new IllegalStateException("Reached the limit of Stars in the Bet : " + stars.toString());
    }

    stars.add(star);
  }

  public void addNumber(Integer number) {

    if (numbers.size() >= NUMBERS_COUNT) {

      throw new IllegalStateException("Reached the limit of Numbers in the Bet : " + numbers.toString());
    }

    numbers.add(number);
  }

  public Algorithm getAlgorithm() {

    return algorithm;
  }

  @Override
  public String toString() {

    if (stars.size() != STARS_COUNT || numbers.size() != NUMBERS_COUNT) {

      throw new IllegalStateException("Bet does not have the correct amount of Stars or Numbers : " + stars.toString()
          + " - " + numbers.toString());
    }

    StringBuilder stringBuilder = new StringBuilder();

    for (Integer number : numbers) {

      stringBuilder.append(String.format("%2s", number).replace(' ', '0'));
      stringBuilder.append(" ");
    }

    int startIndex = 0;

    stringBuilder.append("*");

    for (Integer star : stars) {

      stringBuilder.append(String.format("%2s", star).replace(' ', '0'));

      if (++startIndex > 1) {

        stringBuilder.append("*");

      } else {

        stringBuilder.append(" ");
      }
    }

    return stringBuilder.toString();
  }
}
