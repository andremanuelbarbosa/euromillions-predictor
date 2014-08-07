package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.joda.time.DateTime;
import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;

public class DrawTest extends EuroMillionsPredictorTest {

  private static final String DRAW_LINE_VALID_1 = "1  Tue 05 Aug 2014  05 07 19 21 42 *05 11*  17,029,645";
  private static final String DRAW_LINE_VALID_2 = "5  Tue 05 Aug 2014  05 07 19 21 42 *05 11*  17,029,645";

  private void assertDraw(Draw draw, int expectedIndex) {

    assertNotNull(draw);

    assertEquals(expectedIndex, draw.getIndex());

    assertEquals(new DateTime(2014, 8, 5, 0, 0).toDate(), draw.getDate());

    assertEquals(5, draw.getNumbers().size());
    assertTrue(draw.getNumbers().contains(5));
    assertTrue(draw.getNumbers().contains(7));
    assertTrue(draw.getNumbers().contains(19));
    assertTrue(draw.getNumbers().contains(21));
    assertTrue(draw.getNumbers().contains(42));

    assertEquals(2, draw.getStars().size());
    assertTrue(draw.getStars().contains(5));
    assertTrue(draw.getStars().contains(11));

    assertEquals(17029645l, draw.getJackpot());
  }

  @Test
  public void shouldCreateDrawWhenLineIsValid() throws Exception {

    assertDraw(new Draw(DRAW_LINE_VALID_1), 1);
  }

  @Test
  public void shouldCreateDrawWhenLineIsValidAndHasSpacesAtTheEnd() throws Exception {

    assertDraw(new Draw(DRAW_LINE_VALID_1 + "   "), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowIllegalArgumentExceptionWhenLineIsInvalid() throws Exception {

    new Draw("1  Tue 05 Aug 2014  0507 19 21 42 *05 11*  17,029,645");
  }

  @Test(expected = ParseException.class)
  public void shouldThrowParseExceptionWhenDateInLineHasInvalidFormat() throws Exception {

    new Draw("1  Tue 05 Aun 2014  05 07 19 21 42 *05 11*  17,029,645");
  }

  @Test
  public void shouldReturnTheIndexDifferenceBetweenTwoDrawsWhenCompareTo() throws Exception {

    assertEquals(4, new Draw(DRAW_LINE_VALID_2).compareTo(new Draw(DRAW_LINE_VALID_1)));
  }
}
