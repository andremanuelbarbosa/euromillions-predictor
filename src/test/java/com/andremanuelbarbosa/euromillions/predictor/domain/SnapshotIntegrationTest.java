package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class SnapshotIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private void assertStarsAndNumbers(Snapshot snapshot) {

        assertEquals(snapshot.getStars().size(), Star.COUNT);
        assertEquals(snapshot.getNumbers().size(), Number.COUNT);

        int starIndex = random.nextInt(Star.COUNT);
        int starIndexInterval = snapshot.getDraws().size() - snapshot.getStars().get(starIndex).getInterval() - 1;

        int numberIndex = random.nextInt(Number.COUNT);
        int numberIndexInterval = snapshot.getDraws().size() - snapshot.getNumbers().get(numberIndex).getInterval() - 1;

        assertTrue(snapshot.getDraws().get(starIndexInterval).getStars()
            .contains(snapshot.getStars().get(starIndex).getId()));
        assertTrue(snapshot.getDraws().get(numberIndexInterval).getNumbers()
            .contains(snapshot.getNumbers().get(numberIndex).getId()));

        assertTrue(snapshot.getStars().get(starIndex).getRelativeFreq() > 0);
        assertTrue(snapshot.getNumbers().get(numberIndex).getRelativeFreq() > 0);

        int starsMaximumInterval = snapshot.getStarsMaximumInterval();
        for (Star star : snapshot.getStars()) {
            assertTrue(starsMaximumInterval >= star.getInterval());
        }

        int numbersMaximumInterval = snapshot.getNumbersMaximumInterval();
        for (Number number : snapshot.getNumbers()) {
            assertTrue(numbersMaximumInterval >= number.getInterval());
        }
    }

    @Test
    public void shouldCreateSnapshotFromDraws() {

        final Snapshot snapshot = new Snapshot(RealDraws.getRealDraws(), RealDraws.getRealDraws().get(RealDraws.getRealDraws().size() - 1));

        assertStarsAndNumbers(snapshot);

        snapshot.showStatistics();
    }
}
