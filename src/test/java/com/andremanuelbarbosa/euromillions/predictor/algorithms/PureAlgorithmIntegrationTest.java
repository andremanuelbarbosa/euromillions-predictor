package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;

public class PureAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private PureAlgorithm pureAlgorithm = new PureAlgorithm();

  @Test
  public void shouldReturnNextBet() {

    Bet bet = pureAlgorithm.getNextBet();

    assertNotNull(bet);

    assertTrue(BET_PATTERN.matcher(bet.toString()).matches());
  }
}
