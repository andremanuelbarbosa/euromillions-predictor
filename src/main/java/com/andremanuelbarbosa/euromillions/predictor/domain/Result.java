package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class Result {

  public static final int NUMBERS_COUNT = 5;
  public static final int STARS_COUNT = 2;

  final SortedSet<Integer> numbers = new TreeSet<>();
  final SortedSet<Integer> stars = new TreeSet<>();

  public SortedSet<Integer> getNumbers() {

    return numbers;
  }

  public SortedSet<Integer> getStars() {

    return stars;
  }
}
