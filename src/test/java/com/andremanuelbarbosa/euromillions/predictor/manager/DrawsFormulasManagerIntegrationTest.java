package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.google.common.collect.Lists;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class DrawsFormulasManagerIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static FormulasManager formulasManager;
    private static DrawsFormulasManager drawsFormulasManager;

    @BeforeClass
    public static void setUpBeforeClass() {

        initialize();

        formulasManager = injector.getInstance(FormulasManager.class);
        drawsFormulasManager = injector.getInstance(DrawsFormulasManager.class);
    }

    @Test
    public void shouldOnUpdateDrawFormulas() {

        drawsFormulasManager.updateDrawFormulas(500, Lists.reverse(draws).subList(0, 500), formulasManager.getFormulas());
    }
}
