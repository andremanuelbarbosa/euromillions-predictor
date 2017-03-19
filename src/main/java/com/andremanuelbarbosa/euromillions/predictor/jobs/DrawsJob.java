package com.andremanuelbarbosa.euromillions.predictor.jobs;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawPrize;
import com.andremanuelbarbosa.euromillions.predictor.helper.WebDriverHelper;
import com.andremanuelbarbosa.euromillions.predictor.manager.*;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

// every wed and sat @ 00:00
@Cron("0 * * * * ?")
@DisallowConcurrentExecution
public class DrawsJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawsJob.class);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    private final DrawsManager drawsManager;
    private final DrawsPrizesManager drawsPrizesManager;
    private final DrawsStatsManager drawsStatsManager;
    private final DrawsTemplatesManager drawsTemplatesManager;

    private final FormulasManager formulasManager;
    private final FormulasStatsManager formulasStatsManager;

    private final WebDriverHelper webDriverHelper;

    @Inject
    public DrawsJob(DrawsManager drawsManager, DrawsPrizesManager drawsPrizesManager, DrawsStatsManager drawsStatsManager, DrawsTemplatesManager drawsTemplatesManager, FormulasManager formulasManager, FormulasStatsManager formulasStatsManager, WebDriverHelper webDriverHelper) {

        this.drawsManager = drawsManager;
        this.drawsPrizesManager = drawsPrizesManager;
        this.drawsStatsManager = drawsStatsManager;
        this.drawsTemplatesManager = drawsTemplatesManager;

        this.formulasManager = formulasManager;
        this.formulasStatsManager = formulasStatsManager;

        this.webDriverHelper = webDriverHelper;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOGGER.info("The Draws Job is running...");

        final List<Draw> drawsWithoutStarsOrNumbers = drawsManager.getDrawsWithoutStarsOrNumbers();

        LOGGER.info("Draws without Stars or Numbers: {}.", drawsWithoutStarsOrNumbers.size());

        if (drawsWithoutStarsOrNumbers.size() > 0) {

            final WebDriver webDriver = webDriverHelper.createChromeDriver();

            drawsWithoutStarsOrNumbers.forEach(drawWithoutStarsOrNumbers -> {

                final List<Draw> drawsUpToIncludingDrawWithoutStarsOrNumbers = drawsManager.getDrawsUpToIncluding(drawWithoutStarsOrNumbers.getId());

                drawWithoutStarsOrNumbers.getStars().clear();
                drawWithoutStarsOrNumbers.getNumbers().clear();

                webDriver.get("https://www.euro-millions.com/results/" + SIMPLE_DATE_FORMAT.format(drawWithoutStarsOrNumbers.getDate()));

                final WebElement webElementStarsAndNumbers = webDriver.findElement(By.id("jsBallOrderCell"));

                webElementStarsAndNumbers.findElements(By.className("lucky-star")).forEach(webElementStar -> {

                    drawWithoutStarsOrNumbers.getStars().add(Integer.parseInt(webElementStar.getText()));
                });

                webElementStarsAndNumbers.findElements(By.className("ball")).forEach(webElementNumber -> {

                    drawWithoutStarsOrNumbers.getNumbers().add(Integer.parseInt(webElementNumber.getText()));
                });

                drawWithoutStarsOrNumbers.getPrizes().clear();

                final List<WebElement> webElementPrizes = webDriver.findElement(By.cssSelector(".table.breakdown")).findElements(By.tagName("tr"));

                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(2, 5, Double.parseDouble(webElementPrizes.get(1).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(1, 5, Double.parseDouble(webElementPrizes.get(2).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(0, 5, Double.parseDouble(webElementPrizes.get(3).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(2, 4, Double.parseDouble(webElementPrizes.get(4).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(1, 4, Double.parseDouble(webElementPrizes.get(5).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(0, 4, Double.parseDouble(webElementPrizes.get(6).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(2, 3, Double.parseDouble(webElementPrizes.get(7).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(1, 3, Double.parseDouble(webElementPrizes.get(8).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(2, 2, Double.parseDouble(webElementPrizes.get(9).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(0, 3, Double.parseDouble(webElementPrizes.get(10).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(2, 1, Double.parseDouble(webElementPrizes.get(11).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(1, 2, Double.parseDouble(webElementPrizes.get(12).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));

                if (drawWithoutStarsOrNumbers.getStarsCount() > 10) {

                    drawWithoutStarsOrNumbers.getPrizes().add(new DrawPrize(0, 2, Double.parseDouble(webElementPrizes.get(13).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
                }

                drawsManager.updateDraw(drawWithoutStarsOrNumbers);
                drawsPrizesManager.updateDrawPrizes(drawWithoutStarsOrNumbers.getId(), drawWithoutStarsOrNumbers.getPrizes());

//                drawsStatsManager.updateDrawStats(drawWithoutStarsOrNumbers.getId(), drawsUpToIncludingDrawWithoutStarsOrNumbers);
//                drawsTemplatesManager.updateDrawsTemplates(drawsUpToIncludingDrawWithoutStarsOrNumbers, drawWithoutStarsOrNumbers.getId(), false);

                formulasStatsManager.updateFormulasStats(drawWithoutStarsOrNumbers.getId(),
                    drawsManager.getDrawsUpToIncluding(drawWithoutStarsOrNumbers.getId(), true, true, true), formulasManager.getFormulas());
            });

            webDriver.quit();
        }

        LOGGER.info("The Draws Job has finished.");
    }
}
