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

//        timeMachine = new TimeMachine(REAL_DRAWS);
//        assertAlgorithmsStatistics();

        final TimeMachine timeMachine1 = new TimeMachine(REAL_DRAWS, REAL_DRAWS.size() - 48, REAL_DRAWS.size() - 1);
        timeMachine1.showAlgorithmsStatistics();

        final TimeMachine timeMachine2 = new TimeMachine(REAL_DRAWS, REAL_DRAWS.size() - 96, REAL_DRAWS.size() - 49);
        timeMachine2.showAlgorithmsStatistics();

//        final TimeMachine timeMachine3 = new TimeMachine(REAL_DRAWS, REAL_DRAWS.size() - 73, REAL_DRAWS.size() - 49);
//        timeMachine3.showAlgorithmsStatistics();

//        final TimeMachine timeMachine4 = new TimeMachine(REAL_DRAWS, REAL_DRAWS.size() - 97, REAL_DRAWS.size() - 73);
//        timeMachine4.showAlgorithmsStatistics();
    }
}
