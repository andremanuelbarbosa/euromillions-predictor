package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Draw extends Result {

  private static final Pattern DRAW_PATTERN = Pattern
      .compile("^[ ]*([0-9]{1,3})[ ]{2}([ a-zA-Z0-9]{15})[ ]{2}([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) [*]([0-9]{2}) ([0-9]{2})[*][ ]{1,3}([0-9]{1,3}[,][0-9]{3}[,][0-9]{3})[ ]*$");
  private static final SimpleDateFormat DRAW_SIMPLE_DATE_FORMAT = new SimpleDateFormat("EEE dd MMM yyyy");

  private final int index;
  private final Date date;
  private final Long jackpot;

  public Draw(String line) throws ParseException {

    Matcher matcher = DRAW_PATTERN.matcher(line);

    if (matcher.matches()) {

      index = Integer.parseInt(matcher.group(1));

      date = DRAW_SIMPLE_DATE_FORMAT.parse(matcher.group(2));

      for (int i = 0; i < 5; i++) {
        numbers.add(Integer.parseInt(matcher.group(3 + i)));
      }

      for (int i = 0; i < 2; i++) {
        stars.add(Integer.parseInt(matcher.group(8 + i)));
      }

      jackpot = Long.parseLong(matcher.group(10).replaceAll(",", ""));

    } else {

      throw new IllegalArgumentException("Unable to parse a Draw from line [" + line + "].");
    }
  }

  public int getIndex() {

    return index;
  }

  public Date getDate() {

    return date;
  }

  public Set<Integer> getNumbers() {

    return numbers;
  }

  public Set<Integer> getStars() {

    return stars;
  }

  public long getJackpot() {

    return jackpot;
  }
}
