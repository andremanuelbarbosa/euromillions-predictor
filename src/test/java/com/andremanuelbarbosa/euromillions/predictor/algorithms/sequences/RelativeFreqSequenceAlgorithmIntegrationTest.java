package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.AlgorithmIntegrationTest;
import org.junit.Test;

public class RelativeFreqSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    @Test
    public void should() {

        RelativeFreqSequenceAlgorithm relativeRelativeFreqSequenceAlgorithm = new RelativeFreqSequenceAlgorithm(FULL_SNAPSHOT.getStars().get(0), REAL_DRAWS);

        relativeRelativeFreqSequenceAlgorithm.getNextValue();
    }
}
