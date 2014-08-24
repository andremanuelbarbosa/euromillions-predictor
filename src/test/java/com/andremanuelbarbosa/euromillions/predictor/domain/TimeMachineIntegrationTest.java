package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class TimeMachineIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  private TimeMachine timeMachine;

  private void assertAlgorithmsStatistics(String title) {

    assertNotNull(timeMachine.getAlgorithmsPointsSum());
    assertNotNull(timeMachine.getAlgorithmsMaximumPoints());
    assertNotNull(timeMachine.getAlgorithmsAveragePoints());

    timeMachine.showAlgorithmsPoints(title);
    System.out.println("");
  }

  @Test
  public void shouldReturnAlgorithmsStatisticsForRandomDraws() {

    timeMachine = new TimeMachine(new RandomDraws(RealDraws.getRealDraws().size()).getRandomDraws(),
        Snapshot.DRAWS_COUNT_BEFORE_ELEVEN_STARS + 100);

    assertAlgorithmsStatistics("RANDOM");
  }

  @Test
  public void shouldReturnAlgorithmsStatisticsForRealDraws() {

    timeMachine = new TimeMachine(RealDraws.getRealDraws(), Snapshot.DRAWS_COUNT_BEFORE_ELEVEN_STARS + 100);

    assertAlgorithmsStatistics("REAL");
  }
}
