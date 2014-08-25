package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceSplineInterpolationAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSequenceSplineInterpolationAlgorithm intervalSequenceSplineInterpolationAlgorithm = new IntervalSequenceSplineInterpolationAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceSplineInterpolationAlgorithm);
  }
}
