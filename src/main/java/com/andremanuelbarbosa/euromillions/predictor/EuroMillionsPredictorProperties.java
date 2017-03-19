package com.andremanuelbarbosa.euromillions.predictor;

import org.reflections.scanners.SubTypesScanner;

public abstract class EuroMillionsPredictorProperties {

    public static final int STARS_COUNT_PER_DRAW = 2;
    public static final int NUMBERS_COUNT_PER_DRAW = 5;

    public static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    public static final SubTypesScanner SUB_TYPES_SCANNER_INCLUDE_OBJECT_CLASS = new SubTypesScanner(false);
}
