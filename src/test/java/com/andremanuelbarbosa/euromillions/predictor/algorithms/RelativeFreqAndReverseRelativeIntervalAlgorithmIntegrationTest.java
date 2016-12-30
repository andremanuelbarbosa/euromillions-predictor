package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import com.andremanuelbarbosa.euromillions.predictor.domain.RealDraws;
import org.junit.Before;
import org.junit.Test;

public class RelativeFreqAndReverseRelativeIntervalAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    private RelativeFreqAndReverseRelativeIntervalAlgorithm relativeFreqAndReverseRelativeIntervalAlgorithm;

    @Before
    public void setUp() {

        relativeFreqAndReverseRelativeIntervalAlgorithm = new RelativeFreqAndReverseRelativeIntervalAlgorithm(FULL_SNAPSHOT);
    }

    @Test
    public void shouldReturnNextBet() {

        System.out.println(relativeFreqAndReverseRelativeIntervalAlgorithm.getNextBet());
    }
}
