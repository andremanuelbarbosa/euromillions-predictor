package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSequenceLinearInterpolationAlgorithm intervalSequenceAlgorithm = new IntervalSequenceLinearInterpolationAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceAlgorithm);
  }
}
