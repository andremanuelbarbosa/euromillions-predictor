package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalLoessInterpolationSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalLoessInterpolationSequenceAlgorithm intervalLoessInterpolationSequenceAlgorithm = new IntervalLoessInterpolationSequenceAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalLoessInterpolationSequenceAlgorithm);
  }
}
