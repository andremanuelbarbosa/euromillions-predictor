package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class TimeMachineIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  private TimeMachine timeMachine;

  @Before
  public void setUp() {

    timeMachine = new TimeMachine(Draws.getDraws());
  }

  @Test
  public void shouldReturnMaximumPointsAlgorithm() {

    assertNotNull(timeMachine.getMaximumPointsAlgorithm());
  }
}
