package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class DrawsIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  @Test
  public void shouldLoadDrawsAndStatistics() {

    assertTrue(Draws.getDraws().size() > 0);
  }

  @Test
  public void shouldLoadDrawsOrderedAscending() {

    for (int i = 0; i < Draws.getDraws().size() - 1; i++) {

      assertTrue(Draws.getDraws().get(i).getIndex() < Draws.getDraws().get(i + 1).getIndex());
    }
  }
}
