package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;

public class ReverseFreqAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private ReverseFreqAlgorithm reverseFreqAlgorithm = new ReverseFreqAlgorithm();

  @Test
  public void shouldReturnNextBet() {

    Bet bet = reverseFreqAlgorithm.getNextBet();

    assertNotNull(bet);

    System.out.println(bet.toString());

    assertTrue(BET_PATTERN.matcher(bet.toString()).matches());
  }
}
