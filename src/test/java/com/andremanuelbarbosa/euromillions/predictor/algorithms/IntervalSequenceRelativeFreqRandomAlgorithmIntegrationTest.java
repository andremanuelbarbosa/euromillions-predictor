package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceRelativeFreqRandomAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSequenceRelativeFreqRandomAlgorithm intervalSequenceRelativeFreqRandomAlgorithm = new IntervalSequenceRelativeFreqRandomAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceRelativeFreqRandomAlgorithm);
  }
}
