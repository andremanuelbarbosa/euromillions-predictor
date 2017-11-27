package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import org.junit.Test;

public class IntervalCyclicDividedDifferenceInterpolationSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    private IntervalCyclicDividedDifferenceInterpolationSequenceAlgorithm intervalSequenceCyclicIntervalDividedDifferenceInterpolationAlgorithm = new IntervalCyclicDividedDifferenceInterpolationSequenceAlgorithm(false);

    @Test
    public void shouldReturnNextBet() {

        assertNextBet(intervalSequenceCyclicIntervalDividedDifferenceInterpolationAlgorithm);
    }
}
