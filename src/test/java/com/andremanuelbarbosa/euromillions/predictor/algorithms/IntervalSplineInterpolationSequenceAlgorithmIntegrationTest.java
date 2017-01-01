package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSplineInterpolationSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSplineInterpolationSequenceAlgorithm intervalSplineInterpolationSequenceAlgorithm = new IntervalSplineInterpolationSequenceAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSplineInterpolationSequenceAlgorithm);
  }
}
