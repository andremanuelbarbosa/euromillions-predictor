package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class RealDrawsIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  @Test
  public void shouldLoadDrawsAndStatistics() {

    assertTrue(RealDraws.getRealDraws().size() > 0);
  }

  @Test
  public void shouldLoadDrawsOrderedAscending() {

    for (int i = 0; i < RealDraws.getRealDraws().size() - 1; i++) {

      assertTrue(RealDraws.getRealDraws().get(i).getIndex() < RealDraws.getRealDraws().get(i + 1).getIndex());
    }
  }
}
