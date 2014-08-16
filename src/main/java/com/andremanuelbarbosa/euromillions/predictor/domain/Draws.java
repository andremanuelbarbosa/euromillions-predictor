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

  private static List<Draw> draws;

  static {

    loadDraws();
  }

  public static List<Draw> getDraws() {

    return draws;
  }

  private static void loadDraws() {

    List<Draw> unorderedDraws = new LinkedList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DRAWS_CSV))) {

      for (String line; (line = bufferedReader.readLine()) != null;) {

        if (!StringUtils.isEmpty(line)) {

          unorderedDraws.add(new Draw(line));
        }
      }

    } catch (Exception e) {

      throw new IllegalStateException(e);
    }

    draws = orderDraws(unorderedDraws);
  }

  private static List<Draw> orderDraws(List<Draw> unorderedDraws) {

    List<Draw> orderedDraws = new LinkedList<>();

    orderedDraws.add(unorderedDraws.get(0));

    for (int i = 1; i < unorderedDraws.size(); i++) {

      orderedDraws.add(unorderedDraws.get(i));

      int previousIndex = i - 1;

      while (previousIndex >= 0 && unorderedDraws.get(i).getIndex() < orderedDraws.get(previousIndex).getIndex()) {

        orderedDraws.set(previousIndex + 1, orderedDraws.get(previousIndex));
        orderedDraws.set(previousIndex--, unorderedDraws.get(i));
      }
    }

    return orderedDraws;
  }
}
