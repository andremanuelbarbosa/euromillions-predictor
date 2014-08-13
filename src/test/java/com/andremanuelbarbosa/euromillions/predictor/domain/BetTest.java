package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;
import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;
import com.andremanuelbarbosa.euromillions.predictor.algorithms.PureAlgorithm;

public class BetTest extends EuroMillionsPredictorTest {

  @Mock
  private Snapshot snapshot;

  private final Algorithm algorithm = new PureAlgorithm(snapshot);
  private final Bet bet = new Bet(algorithm);

  @Test
  public void shouldReturnAlgorithm() {

    assertEquals(algorithm, bet.getAlgorithm());
  }
}
