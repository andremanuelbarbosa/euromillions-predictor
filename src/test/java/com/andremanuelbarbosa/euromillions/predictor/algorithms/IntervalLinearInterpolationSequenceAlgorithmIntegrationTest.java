package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalLinearInterpolationSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    private IntervalLinearInterpolationSequenceAlgorithm intervalLinearInterpolationSequenceAlgorithm = new IntervalLinearInterpolationSequenceAlgorithm(false);

    @Test
    public void shouldReturnNextBet() {

        assertNextBet(intervalLinearInterpolationSequenceAlgorithm);
    }
}
