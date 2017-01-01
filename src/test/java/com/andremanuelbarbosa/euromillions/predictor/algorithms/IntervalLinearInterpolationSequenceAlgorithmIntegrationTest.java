package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalLinearInterpolationSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalLinearInterpolationSequenceAlgorithm intervalLinearInterpolationSequenceAlgorithm = new IntervalLinearInterpolationSequenceAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalLinearInterpolationSequenceAlgorithm);
  }
}
