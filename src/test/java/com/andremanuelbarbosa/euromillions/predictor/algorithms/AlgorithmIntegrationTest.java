package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import java.util.regex.Pattern;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

public abstract class AlgorithmIntegrationTest extends EuroMillionsPredictorIntegrationTest {

  static final Pattern BET_PATTERN = Pattern
      .compile("^([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) [*]([0-9]{2}) ([0-9]{2})[*]$");
}
