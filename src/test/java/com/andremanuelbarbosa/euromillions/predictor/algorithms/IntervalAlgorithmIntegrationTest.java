package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalAlgorithm intervalAlgorithm = new IntervalAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalAlgorithm);
  }
}
