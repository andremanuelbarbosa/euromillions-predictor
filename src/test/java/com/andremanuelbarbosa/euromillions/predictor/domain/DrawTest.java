package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.joda.time.DateTime;
import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;

public class DrawTest extends EuroMillionsPredictorTest {

  @Test
  public void shouldCreateDrawWhenLineIsValid() throws Exception {

    Draw draw = new Draw("717  Tue 05 Aug 2014  05 07 19 21 42 *05 11*  17,029,645");

    assertNotNull(draw);
    assertEquals(717, draw.getIndex());
    assertEquals(new DateTime(2014, 8, 5, 0, 0).toDate(), draw.getDate());

  }
}
