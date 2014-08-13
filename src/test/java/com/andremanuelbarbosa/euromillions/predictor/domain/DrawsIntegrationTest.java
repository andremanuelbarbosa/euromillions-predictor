package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class DrawsIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  private final Random random = new Random();

  @Test
  public void shouldLoadDrawsAndStatistics() {

    assertTrue(Draws.getDraws().size() > 0);

    assertEquals(Draws.getStars().size(), 11);
    assertEquals(Draws.getNumbers().size(), 50);

    int starIndex = random.nextInt(Draws.getStars().size());
    int starIndexInterval = Draws.getDraws().size() - Draws.getStars().get(starIndex).getInterval() - 1;

    int numberIndex = random.nextInt(Draws.getNumbers().size());
    int numberIndexInterval = Draws.getDraws().size() - Draws.getNumbers().get(numberIndex).getInterval() - 1;

    assertTrue(Draws.getDraws().get(starIndexInterval).getStars().contains(Draws.getStars().get(starIndex).getId()));
    assertTrue(Draws.getDraws().get(numberIndexInterval).getNumbers().contains(Draws.getNumbers().get(numberIndex).getId()));

    assertTrue(Draws.getStars().get(starIndex).getRelativeFreq() > 0);
    assertTrue(Draws.getNumbers().get(numberIndex).getRelativeFreq() > 0);
  }
}
