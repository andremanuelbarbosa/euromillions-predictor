package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceLoessInterpolationAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSequenceLoessInterpolationAlgorithm intervalSequenceLoessInterpolationAlgorithm = new IntervalSequenceLoessInterpolationAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceLoessInterpolationAlgorithm);
  }
}
