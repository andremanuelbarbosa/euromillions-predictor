package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public class SnapshotIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  private final Snapshot snapshot = new Snapshot(Draws.getDraws());

  @Test
  public void shouldLoadStarsAndNumbers() {

    assertEquals(snapshot.getStars().size(), Star.COUNT);
    assertEquals(snapshot.getNumbers().size(), Number.COUNT);

    int starIndex = random.nextInt(Star.COUNT);
    int starIndexInterval = snapshot.getStars().get(starIndex).getInterval();

    int numberIndex = random.nextInt(Number.COUNT);
    int numberIndexInterval = snapshot.getNumbers().get(numberIndex).getInterval();

    assertTrue(snapshot.getDraws().get(starIndexInterval).getStars()
        .contains(snapshot.getStars().get(starIndex).getId()));
    assertTrue(snapshot.getDraws().get(numberIndexInterval).getNumbers()
        .contains(snapshot.getNumbers().get(numberIndex).getId()));

    assertTrue(snapshot.getStars().get(starIndex).getRelativeFreq() > 0);
    assertTrue(snapshot.getNumbers().get(numberIndex).getRelativeFreq() > 0);
  }
}
