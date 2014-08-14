package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class RelativeFreqAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private RelativeFreqAlgorithm relativeFreqAlgorithm = new RelativeFreqAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(relativeFreqAlgorithm);
  }
}
