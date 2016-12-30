package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.AlgorithmIntegrationTest;
import com.andremanuelbarbosa.euromillions.predictor.domain.Star;

public class SequenceLinearInterpolationAlgorithmTest extends AlgorithmIntegrationTest {

  private static final Integer[] SEQUENCE_ITEMS = { 1, 2, 4, 0, 4, 3, 4 };

  private SequenceLinearInterpolationAlgorithm sequenceLinearInterpolationAlgorithm;

  @Before
  public void setUp() {

    List<Integer> intervals = new LinkedList<>();

    for (Integer item : SEQUENCE_ITEMS) {

      intervals.add(item);
    }

    sequenceLinearInterpolationAlgorithm = new SequenceLinearInterpolationAlgorithm(new Star(1, 3, 1, 0.025, intervals),
        FULL_SNAPSHOT.getDraws());
  }

  @Test
  public void shouldReturnMaximumValue() {

    assertEquals(Integer.valueOf(4), sequenceLinearInterpolationAlgorithm.getMaximumValue());
  }

  @Test
  public void shouldLoadAndReturnValuesFreq() {

    Map<Integer, Integer> valuesFreq = new HashMap<>();
    valuesFreq.put(0, 1);
    valuesFreq.put(1, 1);
    valuesFreq.put(2, 1);
    valuesFreq.put(3, 1);
    valuesFreq.put(4, 3);

    assertEquals(valuesFreq, sequenceLinearInterpolationAlgorithm.getValuesFreq());
  }

  @Test
  public void shouldCalculateAndReturnNextValue() {

    assertEquals(Double.valueOf(4.25), Double.valueOf(sequenceLinearInterpolationAlgorithm.getNextValue()));
    assertEquals(4, (int) Math.round(sequenceLinearInterpolationAlgorithm.getNextValue()));
  }
}
