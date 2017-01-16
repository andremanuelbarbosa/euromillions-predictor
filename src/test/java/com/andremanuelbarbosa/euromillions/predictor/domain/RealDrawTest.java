package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;

public class RealDrawTest extends EuroMillionsPredictorTest {

  private static final String DRAW_LINE_VALID = "1  Tue 05 Aug 2014  05 07 19 21 42 *05 11*  17,029,645";

  private void assertRealDraw(RealDraw realDraw, int expectedIndex) {

    assertNotNull(realDraw);

    assertEquals(expectedIndex, realDraw.getId());

    assertEquals(new DateTime(2014, 8, 5, 0, 0).toDate(), realDraw.getDate());

    assertEquals(5, realDraw.getNumbers().size());
    assertTrue(realDraw.getNumbers().contains(5));
    assertTrue(realDraw.getNumbers().contains(7));
    assertTrue(realDraw.getNumbers().contains(19));
    assertTrue(realDraw.getNumbers().contains(21));
    assertTrue(realDraw.getNumbers().contains(42));

    assertEquals(2, realDraw.getStars().size());
    assertTrue(realDraw.getStars().contains(5));
    assertTrue(realDraw.getStars().contains(11));

    assertEquals(17029645l, realDraw.getJackpot());
  }

  @Test
  public void shouldCreateDrawWhenLineIsValid() throws Exception {

    assertRealDraw(new RealDraw(DRAW_LINE_VALID), 1);
  }

  @Test
  public void shouldCreateDrawWhenLineIsValidAndHasSpacesAtTheEnd() throws Exception {

    assertRealDraw(new RealDraw(DRAW_LINE_VALID + "   "), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenLineIsInvalid() throws Exception {

    new RealDraw("1  Tue 05 Aug 2014  0507 19 21 42 *05 11*  17,029,645");
  }

  @Test(expected = ParseException.class)
  public void shouldThrowParseExceptionWhenDateInLineHasInvalidFormat() throws Exception {

    new RealDraw("1  Tue 05 Aun 2014  05 07 19 21 42 *05 11*  17,029,645");
  }
}
