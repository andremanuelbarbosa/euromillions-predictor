package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.AVAILABLE_PROCESSORS;

public class DrawsTemplatesManagerIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static DrawsTemplatesManager drawsTemplatesManager;

    @BeforeClass
    public static void setUpBeforeClass() {

        initialize();

        drawsTemplatesManager = injector.getInstance(DrawsTemplatesManager.class);
    }

    @Test
    public void shouldCreateDrawsTemplatesForDrawsWithoutTemplates() {

        final ExecutorService executorService = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

        drawsTemplatesManager.getDrawIdsWithoutTemplates().forEach(drawId -> {

            executorService.execute(new DrawsTemplatesManagerIntegrationTestThread(drawId));
        });

        executorService.shutdown();

        try {

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }
    }

    class DrawsTemplatesManagerIntegrationTestThread implements Runnable {

        private final int drawId;

        DrawsTemplatesManagerIntegrationTestThread(int drawId) {

            this.drawId = drawId;
        }

        @Override
        public void run() {

            System.out.println("Generating the Templates for Draw with ID [" + drawId + "]...");

            drawsTemplatesManager.updateDrawsTemplates(drawsReversed.subList(0, drawId), drawId, false);

            System.out.println("The Templates for Draw with ID [" + drawId + "] have been generated.");
        }
    }
}
