package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class RandomAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private RandomAlgorithm randomAlgorithm = new RandomAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(randomAlgorithm);
  }
}
