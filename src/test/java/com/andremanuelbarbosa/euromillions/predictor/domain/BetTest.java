package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;
import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;
import com.andremanuelbarbosa.euromillions.predictor.algorithms.RandomAlgorithm;

public class BetTest extends EuroMillionsPredictorTest {

  @Mock
  private Snapshot snapshot;

  private final Algorithm algorithm = new RandomAlgorithm(snapshot);
  private final Bet bet = new Bet(algorithm);

  @Test
  public void shouldReturnAlgorithm() {

    assertEquals(algorithm, bet.getAlgorithm());
  }
}
