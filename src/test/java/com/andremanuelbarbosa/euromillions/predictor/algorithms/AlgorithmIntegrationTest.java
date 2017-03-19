package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import org.junit.BeforeClass;

public abstract class AlgorithmIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    @BeforeClass
    public static void setUpBeforeClass() {

        initialize();
    }

    void assertNextBet(Algorithm algorithm) {

    }
}
