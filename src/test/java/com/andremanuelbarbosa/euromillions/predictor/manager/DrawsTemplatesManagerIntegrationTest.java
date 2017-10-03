package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
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
    public void shouldCreateDrawsTemplatesForDrawsWithoutTemplates() {

        drawsTemplatesManager.getDrawIdsWithoutTemplates().forEach(drawId -> {

            System.out.println("Generating the Templates for Draw with ID [" + drawId + "]...");

            drawsTemplatesManager.updateDrawsTemplates(drawsReversed.subList(0, drawId), drawId, false);

            System.out.println("The Templates for Draw with ID [" + drawId + "] have been generated.");
        });
    }
}
