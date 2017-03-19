package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalLoessInterpolationSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    private IntervalLoessInterpolationSequenceAlgorithm intervalLoessInterpolationSequenceAlgorithm = new IntervalLoessInterpolationSequenceAlgorithm(false);

    @Test
    public void shouldReturnNextBet() {

        assertNextBet(intervalLoessInterpolationSequenceAlgorithm);
    }
}
