package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.google.common.collect.Lists;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public void shouldOnUpdateFormulasStats() {

        for (int i = 1044; i >= 1000; i--) {

            formulasStatsManager.updateFormulasStats(i, Lists.reverse(draws).subList(0, i), formulasManager.getFormulas());
        }

//        formulasStatsManager.updateFormulasStats(990, 5, Lists.reverse(draws),
//        formulasStatsManager.updateFormulasStats(990, 5, Lists.reverse(draws), 985, 989);
//        formulasStatsManager.updateFormulasStats();
//        formulasStatsManager.updateFormulasStats(Lists.reverse(draws), 983, 987);
    }
}
