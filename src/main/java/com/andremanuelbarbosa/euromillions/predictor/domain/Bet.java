package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;

public class Bet extends Result {

  private final Algorithm algorithm;

  public Bet(Algorithm algorithm) {

    this.algorithm = algorithm;
  }

  public void addNumber(Integer number) {

    if (numbers.size() >= 5) {

      throw new IllegalStateException("Reached the limit of Numbers in the Bet : " + numbers.toString());
    }

    numbers.add(number);
  }

  public void addStar(Integer star) {

    if (stars.size() >= 5) {

      throw new IllegalStateException("Reached the limit of Stars in the Bet : " + stars.toString());
    }

    stars.add(star);
  }

  public Algorithm getAlgorithm() {

    return algorithm;
  }

  @Override
  public String toString() {

    if (numbers.size() != NUMBERS_COUNT || stars.size() != STARS_COUNT) {

      throw new IllegalStateException("Bet does not have the correct amount of Numbers or Stars : "
          + numbers.toString() + " - " + stars.toString());
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
