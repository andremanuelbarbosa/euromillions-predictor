package com.andremanuelbarbosa.euromillions.predictor.algorithms.sequences;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.AlgorithmIntegrationTest;
import org.junit.Test;

public class RelativeFreqSequenceAlgorithmIntegrationTest extends AlgorithmIntegrationTest {

    @Test
    public void should() {

        RelativeFreqSequenceAlgorithm relativeRelativeFreqSequenceAlgorithm = new RelativeFreqSequenceAlgorithm(draws, draws.get(0).getNumberDrawStats().get(0));

        relativeRelativeFreqSequenceAlgorithm.getNextValue();
    }
}
