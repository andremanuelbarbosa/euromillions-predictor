package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceCyclicDividedDifferenceInterpolationAlgorithmTest extends AlgorithmIntegrationTest {

  private IntervalSequenceCyclicDividedDifferenceInterpolationAlgorithm intervalSequenceCyclicIntervalDividedDifferenceInterpolationAlgorithm = new IntervalSequenceCyclicDividedDifferenceInterpolationAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceCyclicIntervalDividedDifferenceInterpolationAlgorithm);
  }
}
