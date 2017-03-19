package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import org.junit.BeforeClass;

public abstract class ManagerIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    @BeforeClass
    public static void setUpBeforeClass() {

        initialize();
    }
}
