package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class RelativeFreqAndIntervalAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private RelativeFreqAndIntervalAlgorithm relativeFreqAndIntervalAlgorithm = new RelativeFreqAndIntervalAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(relativeFreqAndIntervalAlgorithm);
  }
}
