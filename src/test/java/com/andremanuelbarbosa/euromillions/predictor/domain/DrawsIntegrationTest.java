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

    assertEquals(Draws.getNumbers().size(), 50);
    assertEquals(Draws.getStars().size(), 11);

    assertTrue(Draws.getNumbers().get(random.nextInt(Draws.getNumbers().size())).getRelativeFreq() > 0);
    assertTrue(Draws.getStars().get(random.nextInt(Draws.getStars().size())).getRelativeFreq() > 0);

    System.out.println(Draws.getNumbers());
    System.out.println(Draws.getStars());
  }
}
