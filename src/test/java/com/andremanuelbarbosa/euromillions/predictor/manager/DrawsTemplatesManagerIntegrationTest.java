package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.google.common.collect.Lists;
import org.junit.BeforeClass;
import org.junit.Test;

public class DrawsTemplatesManagerIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static DrawsTemplatesManager drawsTemplatesManager;

    @BeforeClass
    public static void setUpBeforeClass() {

        initialize();

        drawsTemplatesManager = injector.getInstance(DrawsTemplatesManager.class);
    }

    @Test
    public void shouldUpdateStarsDrawsTemplates() {

        for (int i = 751; i <= 950; i++) {

            drawsTemplatesManager.updateStarsDrawsTemplates(drawsReversed.subList(0, i), i, false);
        }
    }
}
