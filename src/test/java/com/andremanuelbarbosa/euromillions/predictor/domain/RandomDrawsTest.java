package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;

public class RandomDrawsTest extends EuroMillionsPredictorTest {

  private RandomDraws randomDraws;

  @Test
  public void shouldLoadTenDraws() {

    randomDraws = new RandomDraws(10);

    assertEquals(10, randomDraws.getRandomDraws().size());
  }
}
