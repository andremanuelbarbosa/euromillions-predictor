package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;

public class DrawsStatsManagerIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static DrawsStatsManager drawsStatsManager;

    @BeforeClass
    public static void setUpBeforeClass() {

        initialize();

        drawsStatsManager = injector.getInstance(DrawsStatsManager.class);
    }

    @Test
    public void shouldCreateDrawStatsForDrawsWithoutStats() {

        drawsStatsManager.getDrawIdsWithoutStats().forEach(drawId -> {

            System.out.println("Generating the Stats for Draw with ID [" + drawId + "]...");

            drawsStatsManager.updateDrawStats(drawId, drawsManager.getDrawsUpToIncluding(drawId));

            System.out.println("The Stats for Draw with ID [" + drawId + "] have been generated.");
        });
    }
}
