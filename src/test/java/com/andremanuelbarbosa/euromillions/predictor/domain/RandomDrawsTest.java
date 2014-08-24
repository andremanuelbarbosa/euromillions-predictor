package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;

public class RandomDrawsTest extends EuroMillionsPredictorTest {

  private RandomDraws randomDraws;

  @Test
  public void shouldGenerateTenDraws() {

    randomDraws = new RandomDraws(10);

    assertEquals(10, randomDraws.getRandomDraws().size());
  }

  private void measureGenerationTime(int numRandomDraws) {

    long startTime = System.currentTimeMillis();

    randomDraws = new RandomDraws(numRandomDraws);

    double generationTime = System.currentTimeMillis() - startTime;
    double averageGenerationTime = (double) generationTime / startTime;

    assertEquals(numRandomDraws, randomDraws.getRandomDraws().size());

    System.out.println("Generation Time for " + numRandomDraws + " Random Draws: " + generationTime + "ms (Average: "
        + averageGenerationTime + "ms)");
  }

  @Test
  public void shouldMeasureGenerationTime() {

    measureGenerationTime(100);
    measureGenerationTime(1000);
  }
}
