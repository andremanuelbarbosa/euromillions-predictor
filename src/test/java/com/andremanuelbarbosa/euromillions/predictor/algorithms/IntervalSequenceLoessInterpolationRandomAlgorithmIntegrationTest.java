package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceLoessInterpolationRandomAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSequenceLoessInterpolationRandomAlgorithm intervalSequenceLoessInterpolationRandomAlgorithm = new IntervalSequenceLoessInterpolationRandomAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceLoessInterpolationRandomAlgorithm);
  }
}
