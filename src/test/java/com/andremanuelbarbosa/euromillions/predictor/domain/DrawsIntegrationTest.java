package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class DrawsIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  @Test
  public void shouldLoadDrawsAndStatistics() {

    assertTrue(Draws.getDraws().size() > 0);
  }
}
