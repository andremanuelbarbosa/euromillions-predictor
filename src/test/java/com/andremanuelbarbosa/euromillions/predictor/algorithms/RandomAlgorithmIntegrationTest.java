package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class RandomAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private RandomAlgorithm randomAlgorithm = new RandomAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(randomAlgorithm);
  }

  @Test
  public void shouldReturnTwoDifferentBetsForConsecutiveRequests() {

    assertFalse(randomAlgorithm.getNextBet().equals(randomAlgorithm.getNextBet()));
  }
}
