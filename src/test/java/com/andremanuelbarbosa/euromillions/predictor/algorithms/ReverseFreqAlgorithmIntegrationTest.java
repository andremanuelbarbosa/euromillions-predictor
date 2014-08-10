package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draws;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class ReverseFreqAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private ReverseFreqAlgorithm reverseFreqAlgorithm = new ReverseFreqAlgorithm();

  @Test
  public void shouldReturnNextBet() {

    Bet bet = reverseFreqAlgorithm.getNextBet();

    assertNotNull(bet);

    System.out.println(bet.toString());

    for (Number number : Draws.getNumbers()) {

      if (!bet.getNumbers().contains(number.getId())) {

        for (Integer betNumber : bet.getNumbers()) {

          assertTrue(number.getRelativeFreq() >= Draws.getNumbers().get(betNumber - 1).getRelativeFreq());
        }
      }
    }

    for (Star star : Draws.getStars()) {

      if (!bet.getStars().contains(star.getId())) {

        for (Integer betStar : bet.getStars()) {

          assertTrue(star.getRelativeFreq() >= Draws.getStars().get(betStar - 1).getRelativeFreq());
        }
      }
    }

    assertTrue(BET_PATTERN.matcher(bet.toString()).matches());
  }
}
