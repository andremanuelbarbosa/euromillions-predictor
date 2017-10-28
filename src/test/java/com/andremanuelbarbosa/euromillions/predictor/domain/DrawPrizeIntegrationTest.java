package com.andremanuelbarbosa.euromillions.predictor.domain;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.andremanuelbarbosa.euromillions.predictor.steps.EuroMillionsPredictorSteps;
import com.google.common.collect.Lists;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.List;

public class DrawPrizeIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    private final EuroMillionsPredictorSteps euroMillionsPredictorSteps = new EuroMillionsPredictorSteps();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        initialize();
    }

    private String getPrizeInsert(int drawId, int stars, int numbers, WebElement prizeTableRow) {

        return "        <insert tableName=\"draws_prizes\"><column name=\"draw_id\" value=\"" + drawId + "\"></column><column name=\"stars\" value=\"" + stars + "\"></column><column name=\"numbers\" value=\"" + numbers + "\"></column><column name=\"prize\" value=\"" + prizeTableRow.findElements(By.tagName("td")).get(1).getText().replaceAll("[£€,]", "") + "\"></column></insert>";
    }

    @Test
    public void should() throws Throwable {

        List<List<String>> prizesInserts = Lists.newArrayListWithExpectedSize(draws.size());

        for (int i = draws.size() - 1054; i >= 0; i--) {

            final Draw draw = draws.get(i);

            euroMillionsPredictorSteps.getWebDriver().get("https://www.euro-millions.com/results/" + SIMPLE_DATE_FORMAT.format(draw.getDate()));

            final List<String> drawPrizesInserts = Lists.newArrayListWithExpectedSize(12);

            final List<WebElement> webElements = euroMillionsPredictorSteps.getWebDriver().findElement(By.cssSelector(".table.breakdown")).findElements(By.tagName("tr"));

            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 2, 5, webElements.get(1)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 1, 5, webElements.get(2)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 0, 5, webElements.get(3)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 2, 4, webElements.get(4)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 1, 4, webElements.get(5)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 0, 4, webElements.get(6)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 2, 3, webElements.get(7)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 1, 3, webElements.get(8)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 2, 2, webElements.get(9)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 0, 3, webElements.get(10)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 2, 1, webElements.get(11)));
            drawPrizesInserts.add(getPrizeInsert(draw.getId(), 1, 2, webElements.get(12)));

            if(draw.getStarsCount() > 10) {

                drawPrizesInserts.add(getPrizeInsert(draw.getId(), 0, 2, webElements.get(13)));
            }

            prizesInserts.add(drawPrizesInserts);

            Thread.sleep(2000);
        }

        System.out.println();

        prizesInserts.forEach(drawPrizeInserts -> {

            drawPrizeInserts.forEach(drawPrizeInsert -> {

                System.out.println(drawPrizeInsert);
            });

            System.out.println();
        });
    }
}
