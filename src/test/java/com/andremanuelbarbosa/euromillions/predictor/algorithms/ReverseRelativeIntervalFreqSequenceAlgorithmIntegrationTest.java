package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class ReverseRelativeIntervalFreqSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private ReverseRelativeIntervalFreqSequenceAlgorithm reverseRelativeIntervalFreqSequenceAlgorithm = new ReverseRelativeIntervalFreqSequenceAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(reverseRelativeIntervalFreqSequenceAlgorithm);
  }
}
