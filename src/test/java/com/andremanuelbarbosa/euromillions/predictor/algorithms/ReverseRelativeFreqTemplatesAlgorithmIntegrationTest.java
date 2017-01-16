package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class ReverseRelativeFreqTemplatesAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    private final ReverseRelativeFreqTemplatesAlgorithm reverseRelativeFreqTemplatesAlgorithm = new ReverseRelativeFreqTemplatesAlgorithm(FULL_SNAPSHOT);

    @Test
    public void shouldReturnNextBet() {

        assertNextBet(reverseRelativeFreqTemplatesAlgorithm);
    }
}
