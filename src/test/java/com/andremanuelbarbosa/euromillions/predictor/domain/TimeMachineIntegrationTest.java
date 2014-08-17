package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class TimeMachineIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  private TimeMachine timeMachine;

  private void assertAlgorithmsStatistics() {

    assertNotNull(timeMachine.getAlgorithmsPointsSum());
    assertNotNull(timeMachine.getAlgorithmsMaximumPoints());
    assertNotNull(timeMachine.getAlgorithmsAveragePoints());

    System.out.println(timeMachine.getDraws().get(0).getClass().getName());
    System.out.println("Points Sum : " + timeMachine.getAlgorithmsPointsSum().toString());
    System.out.println("Maximum Points : " + timeMachine.getAlgorithmsMaximumPoints().toString());
    System.out.println("Average Points : " + timeMachine.getAlgorithmsAveragePoints().toString());
    System.out.println("");
  }

  @Test
  public void shouldReturnAlgorithmsStatisticsForRandomDraws() {

    timeMachine = new TimeMachine(new RandomDraws(2000).getRandomDraws(), 1000);

    assertAlgorithmsStatistics();
  }

  @Test
  public void shouldReturnAlgorithmsStatisticsForRealDraws() {

    timeMachine = new TimeMachine(RealDraws.getRealDraws(), 100);

    assertAlgorithmsStatistics();
  }
}
