package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RealDraw extends Draw {

  private static final Pattern DRAW_PATTERN = Pattern
      .compile("^[ ]*([0-9]{1,3})[ ]{2}([ a-zA-Z0-9]{15})[ ]{2}([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) [*]([0-9]{2}) ([0-9]{2})[*][ ]{1,3}([0-9]{1,3}[,][0-9]{3}[,][0-9]{3})[ ]*$");
  private static final SimpleDateFormat DRAW_SIMPLE_DATE_FORMAT = new SimpleDateFormat("EEE dd MMM yyyy");

  private final Date date;
  private final Long jackpot;

  public RealDraw(String line) throws ParseException {

    Matcher matcher = DRAW_PATTERN.matcher(line);

    if (matcher.matches()) {

      setIndex(Integer.parseInt(matcher.group(1)));

      date = DRAW_SIMPLE_DATE_FORMAT.parse(matcher.group(2));

      for (int i = 0; i < Result.NUMBERS_COUNT; i++) {

        numbers.add(Integer.parseInt(matcher.group(3 + i)));
      }

      for (int i = 0; i < Result.STARS_COUNT; i++) {

        stars.add(Integer.parseInt(matcher.group(8 + i)));
      }

      jackpot = Long.parseLong(matcher.group(10).replaceAll(",", ""));

    } else {

      throw new IllegalArgumentException("Unable to parse a Real Draw from line [" + line + "].");
    }
  }

  public Date getDate() {

    return date;
  }

  public long getJackpot() {

    return jackpot;
  }
}
