package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.google.common.collect.Lists;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.AVAILABLE_PROCESSORS;

public class FormulasStatsManagerIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static FormulasManager formulasManager;
    private static FormulasStatsManager formulasStatsManager;

    @BeforeClass
    public static void setUpBeforeClass() {

        initialize();

        formulasManager = injector.getInstance(FormulasManager.class);
        formulasStatsManager = injector.getInstance(FormulasStatsManager.class);
    }

    @Test
    public void shouldCreateFormulasStatsForDrawsWithoutFormulasStats() {

        final ExecutorService executorService = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

        formulasStatsManager.getDrawIdsWithoutFormulasStats().forEach(drawId -> {

            executorService.execute(new FormulasStatsManagerIntegrationTestThead(drawId));
        });

        executorService.shutdown();

        try {

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }
    }

    class FormulasStatsManagerIntegrationTestThead implements Runnable {

        private final int drawId;

        FormulasStatsManagerIntegrationTestThead(int drawId) {

            this.drawId = drawId;
        }

        @Override
        public void run() {

            System.out.println("Generating the Formulas Stats for Draw with ID [" + drawId + "]...");

            formulasStatsManager.updateFormulasStats(drawId, Lists.reverse(draws).subList(0, drawId), formulasManager.getFormulas());

            System.out.println("The Formulas Stats for Draw with ID [" + drawId + "] have been generated.");
        }
    }
}
