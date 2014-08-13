package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class DrawsIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  @Test
  public void shouldLoadDrawsOrderedDescending() {

    assertEquals(Star.COUNT, Draws.getStars().size());
    assertEquals(Number.COUNT, Draws.getNumbers().size());

    Draw drawPrevious = null;

    for (Draw draw : Draws.getDraws()) {

      if (drawPrevious != null) {

        assertTrue(draw.getIndex() < drawPrevious.getIndex());
      }

      drawPrevious = draw;
    }
  }

  @Test
  public void shouldLoadDrawsStatistics() {

    for (Star star : Draws.getStars()) {

      assertTrue(star.getRelativeFreq() > 0);
      assertTrue(Draws.getDraws().get(star.getInterval()).getStars().contains(star.getId()));
    }

    for (Number number : Draws.getNumbers()) {

      assertTrue(number.getRelativeFreq() > 0);
      assertTrue(Draws.getDraws().get(number.getInterval()).getNumbers().contains(number.getId()));
    }
  }
}
