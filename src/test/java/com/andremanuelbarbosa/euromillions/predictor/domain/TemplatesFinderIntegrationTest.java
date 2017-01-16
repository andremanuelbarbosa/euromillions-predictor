package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemplatesFinderIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private final TemplatesFinder templatesFinder = new TemplatesFinder(REAL_DRAWS);

    @Test
    public void souldReturnTwoStarTemplatesOnGetStarTemplates() {

        assertEquals(2, templatesFinder.getStarTemplates().size());
    }

    @Test
    public void souldReturnFiveNumberTemplatesOnGetNumberTemplates() {

        assertEquals(5, templatesFinder.getNumberTemplates().size());
    }
}
