package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class ReverseRelativeFreqAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private ReverseRelativeFreqAlgorithm relativeFreqAlgorithm = new ReverseRelativeFreqAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(relativeFreqAlgorithm);
  }
}
