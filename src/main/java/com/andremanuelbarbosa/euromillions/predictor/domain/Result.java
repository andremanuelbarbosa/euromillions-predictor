package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.util.HashSet;
import java.util.Set;

public class Result {

  final Set<Integer> numbers = new HashSet<>(5);
  final Set<Integer> stars = new HashSet<>(2);

  public Set<Integer> getNumbers() {

    return numbers;
  }

  public Set<Integer> getStars() {

    return stars;
  }
}
