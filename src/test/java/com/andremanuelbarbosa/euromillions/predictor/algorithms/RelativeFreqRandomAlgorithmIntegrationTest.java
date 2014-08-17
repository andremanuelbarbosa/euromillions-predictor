package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class RelativeFreqRandomAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private RelativeFreqRandomAlgorithm relativeFreqRandomAlgorithm = new RelativeFreqRandomAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(relativeFreqRandomAlgorithm);
  }
}
