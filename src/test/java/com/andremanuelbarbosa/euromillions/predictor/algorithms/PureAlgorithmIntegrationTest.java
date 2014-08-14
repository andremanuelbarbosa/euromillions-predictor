package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class PureAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private PureAlgorithm pureAlgorithm = new PureAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(pureAlgorithm);
  }
}
