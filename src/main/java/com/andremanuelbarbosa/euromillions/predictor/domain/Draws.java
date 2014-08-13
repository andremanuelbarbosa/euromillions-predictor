package com.andremanuelbarbosa.euromillions.predictor.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public abstract class Draws {

  public static final int DRAWS_COUNT_BEFORE_ELEVEN_STARS = 378;
  public static final Date DATE_ELEVEN_STARS = new DateTime(2011, 5, 10, 0, 0, 0).toDate();

  private static final String DRAWS_CSV = "src/main/resources/draws.csv";

  private static final List<Draw> DRAWS = new LinkedList<>();
  private static final List<Number> NUMBERS = new LinkedList<>();
  private static final List<Star> STARS = new LinkedList<>();

  static {

    loadDraws();
    loadStatistics();
    showStatistics();
  }

  public static List<Draw> getDraws() {

    return DRAWS;
  }

  public static List<Number> getNumbers() {

    return NUMBERS;
  }

  public static List<Star> getStars() {

    return STARS;
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

  private static int getFreq(int id, ItemType itemType) {

    int freq = 0;

    for (Draw draw : DRAWS) {

      if (itemType == ItemType.NUMBER) {

        if (draw.getNumbers().contains(id)) {

          freq++;
        }

      } else if (itemType == ItemType.STAR) {

        if (draw.getNumbers().contains(id)) {

          freq++;
        }
      }
    }

    return freq;
  }

  private static int getInterval(int id, ItemType itemType) {

    int interval = 0;

    for (int i = DRAWS.size() - 1; i >= 0; i--) {

      if ((itemType == ItemType.NUMBER && DRAWS.get(i).getNumbers().contains(id))
          || (itemType == ItemType.STAR && DRAWS.get(i).getStars().contains(id))) {

        break;

      } else {

        interval++;
      }
    }

    return interval;
  }

  private static void loadStatistics() {

    for (int i = 1; i <= Number.COUNT; i++) {

      NUMBERS.add(new Number(i, getInterval(i, ItemType.NUMBER), (double) getFreq(i, ItemType.NUMBER) / DRAWS.size()));
    }

    for (int i = 1; i <= Star.COUNT; i++) {

      STARS.add(new Star(i, getInterval(i, ItemType.STAR), i < 10 ? (double) getFreq(i, ItemType.STAR) / DRAWS.size()
          : (double) getFreq(i, ItemType.STAR) / DRAWS_COUNT_BEFORE_ELEVEN_STARS));
    }
  }

  private static void showStatistics() {

    System.out.println("NUMBER | INTERVAL | RELATIVE FREQ");
    System.out.println("---------------------------------");

    for (Number number : NUMBERS) {

      System.out.println(number.getStatisticsLine());
    }

    System.out.println("");
    System.out.println("  STAR | INTERVAL | RELATIVE FREQ");
    System.out.println("---------------------------------");

    for (Star star : STARS) {

      System.out.println(star.getStatisticsLine());
    }
  }
}
