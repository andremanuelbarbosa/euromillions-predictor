package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalSequenceRelativeFreqAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalSequenceRelativeFreqAlgorithm intervalSequenceRelativeFreqAlgorithm = new IntervalSequenceRelativeFreqAlgorithm(
      FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    assertNextBet(intervalSequenceRelativeFreqAlgorithm);
  }
}
