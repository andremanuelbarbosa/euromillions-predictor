package com.andremanuelbarbosa.euromillions.predictor;

import com.andremanuelbarbosa.euromillions.predictor.domain.RealDraw;
import com.andremanuelbarbosa.euromillions.predictor.domain.RealDraws;

import java.util.List;
import java.util.Random;

public abstract class EuroMillionsPredictorIntegrationTest {

    protected static final List<RealDraw> REAL_DRAWS = RealDraws.getRealDraws();

    protected final Random random = new Random();
}
