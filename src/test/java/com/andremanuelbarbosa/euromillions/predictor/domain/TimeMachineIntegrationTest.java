package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TimeMachineIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private TimeMachine timeMachine;

    private void assertAlgorithmsStatistics() {

        assertNotNull(timeMachine.getAlgorithmsPointsSum());
        assertNotNull(timeMachine.getAlgorithmsMaximumPoints());
        assertNotNull(timeMachine.getAlgorithmsAveragePoints());

        timeMachine.showAlgorithmsStatistics();
    }

    @Test
    @Ignore
    public void shouldReturnAlgorithmsStatisticsForRandomDraws() {

        timeMachine = new TimeMachine(new RandomDraws(RealDraws.getRealDraws().size()).getRandomDraws(),
            Snapshot.DRAWS_COUNT_BEFORE_ELEVEN_STARS + 100);

        assertAlgorithmsStatistics();
    }

    @Test
    public void shouldReturnAlgorithmsStatisticsForRealDraws() {

        timeMachine = new TimeMachine(RealDraws.getRealDraws());
//        timeMachine = new TimeMachine(RealDraws.getRealDraws(), RealDraws.getRealDraws().size() - 1);

        assertAlgorithmsStatistics();
    }
}
