package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class TimeMachineIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  private TimeMachine timeMachine;

  private void assertAlgorithmsStatistics() {

    assertNotNull(timeMachine.getAlgorithmsPointsSum());
    assertNotNull(timeMachine.getAlgorithmsMaximumPoints());
    assertNotNull(timeMachine.getAlgorithmsAveragePoints());

    System.out.println(timeMachine.getDraws().get(0).getClass().getSimpleName());
    timeMachine.showAlgorithmsPoints();
    System.out.println("");
  }

  @Test
  @Ignore
  public void shouldReturnAlgorithmsStatisticsForRandomDraws() {

    timeMachine = new TimeMachine(new RandomDraws(RealDraws.getRealDraws().size()).getRandomDraws(), 100);

    assertAlgorithmsStatistics();
  }

  @Test
  public void shouldReturnAlgorithmsStatisticsForRealDraws() {

    timeMachine = new TimeMachine(RealDraws.getRealDraws(), 100);

    assertAlgorithmsStatistics();
  }
}
