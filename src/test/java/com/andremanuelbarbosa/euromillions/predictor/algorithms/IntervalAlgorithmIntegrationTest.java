package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class IntervalAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

  private IntervalAlgorithm intervalAlgorithm = new IntervalAlgorithm(FULL_SNAPSHOT);

  @Test
  public void shouldReturnNextBet() {

    Bet bet = intervalAlgorithm.getNextBet();

    assertNotNull(bet);

    for (Star star : FULL_SNAPSHOT.getStars()) {

      if (!bet.getStars().contains(star.getId())) {

        assertTrue(star.getInterval() <= intervalAlgorithm.getMinimumIntervalFromStars(bet.getStars()));
      }
    }

    for (Number number : FULL_SNAPSHOT.getNumbers()) {

      if (!bet.getNumbers().contains(number.getId())) {

        assertTrue(number.getInterval() <= intervalAlgorithm.getMinimumIntervalFromNumbers(bet.getNumbers()));
      }
    }

    assertTrue(BET_PATTERN.matcher(bet.toString()).matches());
  }
}
