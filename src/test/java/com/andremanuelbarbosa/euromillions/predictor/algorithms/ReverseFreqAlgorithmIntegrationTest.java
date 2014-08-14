package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class ReverseFreqAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private ReverseFreqAlgorithm reverseFreqAlgorithm = new ReverseFreqAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(reverseFreqAlgorithm);
  }
}
