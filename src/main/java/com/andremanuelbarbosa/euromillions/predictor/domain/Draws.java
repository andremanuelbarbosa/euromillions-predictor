package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public abstract class Draws {

  public static final Date DATE_ELEVEN_STARS = new DateTime(2011, 5, 10, 0, 0, 0).toDate();

  private static final String DRAWS_CSV = "src/main/resources/draws.csv";

  private static final List<Draw> DRAWS = new LinkedList<>();

  static {

    loadDraws();
  }

  public static List<Draw> getDraws() {

    return DRAWS;
  }

  private static void loadDraws() {

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DRAWS_CSV))) {

      for (String line; (line = bufferedReader.readLine()) != null;) {

        if (!StringUtils.isEmpty(line)) {

          DRAWS.add(new Draw(line));
        }
      }

    } catch (Exception e) {

      throw new IllegalStateException(e);
    }
  }
}
