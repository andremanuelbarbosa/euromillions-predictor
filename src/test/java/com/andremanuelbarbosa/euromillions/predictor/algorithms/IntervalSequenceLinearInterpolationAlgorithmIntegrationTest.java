package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceLinearInterpolationAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSequenceLinearInterpolationAlgorithm intervalSequenceLinearInterpolationAlgorithm = new IntervalSequenceLinearInterpolationAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceLinearInterpolationAlgorithm);
  }
}
