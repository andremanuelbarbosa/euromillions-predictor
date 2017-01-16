package com.andremanuelbarbosa.euromillions.predictor.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;

import java.text.SimpleDateFormat;

public class RealDrawsIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    @Test
    public void shouldLoadDrawsAndStatistics() {

        assertTrue(RealDraws.getRealDraws().size() > 0);
    }

    @Test
    public void shouldLoadDrawsOrderedAscending() {

        for (int i = 0; i < RealDraws.getRealDraws().size() - 1; i++) {

            assertTrue(RealDraws.getRealDraws().get(i).getId() < RealDraws.getRealDraws().get(i + 1).getId());
        }
    }

    @Test
    public void generate() {

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 900; i < REAL_DRAWS.size(); i++) {
//            for (int i = 0; i < REAL_DRAWS.size(); i++) {

//        REAL_DRAWS.forEach(draw -> {

            Draw draw = REAL_DRAWS.get(i);

            System.out.println("        <insert tableName=\"draws\"><column name=\"id\" value=\"" + draw.getId() + "\"></column><column name=\"date\" value=\"" + simpleDateFormat.format(draw.getDate()) + "\"></column><column name=\"prize\" value=\"" + String.format("%2.0f", draw.getPrize()) + "\"></column></insert>");

            draw.getStars().forEach(star -> {

                System.out.println("        <insert tableName=\"draws_stars\"><column name=\"draw_id\" value=\"" + draw.getId() + "\"></column><column name=\"star\" value=\"" + star + "\"></column></insert>");
            });

            draw.getNumbers().forEach(number -> {

                System.out.println("        <insert tableName=\"draws_numbers\"><column name=\"draw_id\" value=\"" + draw.getId() + "\"></column><column name=\"number\" value=\"" + number + "\"></column></insert>");
            });

            System.out.println("        ");
        }

//        for (int i = 0; i < REAL_DRAWS.size(); i++) {
//
//            System.out.print(REAL_DRAWS.get(i));
//        }
    }
}
