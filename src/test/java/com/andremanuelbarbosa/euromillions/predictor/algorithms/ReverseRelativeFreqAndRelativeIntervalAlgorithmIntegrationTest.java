package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class ReverseRelativeFreqAndRelativeIntervalAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private ReverseRelativeFreqAndRelativeIntervalAlgorithm reverseRelativeFreqAndRelativeIntervalAlgorithm = new ReverseRelativeFreqAndRelativeIntervalAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(reverseRelativeFreqAndRelativeIntervalAlgorithm);
  }
}
