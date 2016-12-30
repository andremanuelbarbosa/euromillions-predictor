package com.andremanuelbarbosa.euromillions.predictor.algorithms;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.regex.Pattern;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.andremanuelbarbosa.euromillions.predictor.domain.*;
import com.andremanuelbarbosa.euromillions.predictor.domain.Number;
import org.junit.BeforeClass;

public abstract class AlgorithmIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static final Pattern BET_PATTERN = Pattern
        .compile("^([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) ([0-9]{2}) [*]([0-9]{2}) ([0-9]{2})[*]$");

    protected static final List<RealDraw> REAL_DRAWS = RealDraws.getRealDraws();
    protected static final Snapshot FULL_SNAPSHOT = new Snapshot(REAL_DRAWS);

    @BeforeClass
    public static void setUpBeforeClass() {

        FULL_SNAPSHOT.run();
    }

    void assertNextBet(Algorithm algorithm) {

        Bet bet = algorithm.getNextBet();

        assertNotNull(bet);

        System.out.println(String.format("%62s  %22s", algorithm.getClass().getSimpleName(), bet.toString()));

        for (Star star : FULL_SNAPSHOT.getStars()) {

            if (!bet.getStars().contains(star.getId())) {

                assertTrue(algorithm.getItemWeight(star) <= algorithm.getMinimumWeight(bet.getStars(), ItemType.STAR));
            }
        }

        for (Number number : FULL_SNAPSHOT.getNumbers()) {

            if (!bet.getNumbers().contains(number.getId())) {

                assertTrue(algorithm.getItemWeight(number) <= algorithm.getMinimumWeight(bet.getNumbers(), ItemType.NUMBER));
            }
        }

        assertTrue(BET_PATTERN.matcher(bet.toString()).matches());
    }
}
