package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.AlgorithmIntegrationTest;
import org.junit.Test;

public class RelativeIntervalFreqSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    @Test
    public void should() {

        RelativeIntervalFreqSequenceAlgorithm relativeIntervalFreqSequenceAlgorithm = new RelativeIntervalFreqSequenceAlgorithm(FULL_SNAPSHOT.getStars().get(0), REAL_DRAWS);

        relativeIntervalFreqSequenceAlgorithm.getNextValue();
    }
}
