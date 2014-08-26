package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.RealDraws;
import com.andremanuelbarbosa.euromillions.predictor.domain.ItemType;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import com.andremanuelbarbosa.euromillions.predictor.domain.Snapshot;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public abstract class AlgorithmIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  private static final Pattern BET_PATTERN = Pattern
      .compile("^([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) [*]([0-9]{2}) ([0-9]{2})[*]$");

  protected static final Snapshot FULL_SNAPSHOT = new Snapshot(RealDraws.getRealDraws());

  void assertNextBet(Algorithm algorithm) {

    Bet bet = algorithm.getNextBet();

    assertNotNull(bet);

    System.out.println(String.format("%63s  %22s", algorithm.getClass().getSimpleName(), bet.toString()));

    for (Star star : FULL_SNAPSHOT.getStars()) {

      if (!bet.getStars().contains(star.getId())) {

        assertTrue(algorithm.getItemWeight(star) <= algorithm.getMinimumWeight(bet.getStars(), ItemType.STAR));
      }
    }

    for (Number number : FULL_SNAPSHOT.getNumbers()) {

      if (!bet.getNumbers().contains(number.getId())) {

        assertTrue(algorithm.getItemWeight(number) <= algorithm.getMinimumWeight(bet.getNumbers(), ItemType.NUMBER));
      }
    }

    assertTrue(BET_PATTERN.matcher(bet.toString()).matches());
  }
}
