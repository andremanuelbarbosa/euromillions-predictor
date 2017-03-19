package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FormulasManagerTest extends EuroMillionsPredictorTest {

    private final AlgorithmsManager algorithmsManager = new AlgorithmsManager();

    private FormulasManager formulasManager;

    @Before
    public void setUp() throws Exception {

        formulasManager = new FormulasManager(algorithmsManager);
    }

    @Test
    public void shouldReturnFormulasOnGetFormulas() throws Exception {

        assertTrue(formulasManager.getFormulas().size() > 0);
    }
}
