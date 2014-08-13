package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.SortedSet;
import java.util.TreeSet;

public class Result {

  public static final int STARS_COUNT = 2;
  public static final int NUMBERS_COUNT = 5;

  final SortedSet<Integer> stars = new TreeSet<>();
  final SortedSet<Integer> numbers = new TreeSet<>();

  public SortedSet<Integer> getStars() {

    return stars;
  }

  public SortedSet<Integer> getNumbers() {

    return numbers;
  }
}
