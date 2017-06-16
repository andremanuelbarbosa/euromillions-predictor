package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawPrize;
import com.andremanuelbarbosa.euromillions.predictor.helper.WebDriverHelper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.List;

@Singleton
public class DrawsManager {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    private final DrawsDao drawsDao;
    private final DrawsStatsManager drawsStatsManager;
    private final DrawsPrizesManager drawsPrizesManager;
    private final DrawsTemplatesManager drawsTemplatesManager;

    private final WebDriverHelper webDriverHelper;

    @Inject
    public DrawsManager(DrawsDao drawsDao, DrawsStatsManager drawsStatsManager, DrawsPrizesManager drawsPrizesManager, DrawsTemplatesManager drawsTemplatesManager, WebDriverHelper webDriverHelper) {

        this.drawsDao = drawsDao;
        this.drawsStatsManager = drawsStatsManager;
        this.drawsPrizesManager = drawsPrizesManager;
        this.drawsTemplatesManager = drawsTemplatesManager;

        this.webDriverHelper = webDriverHelper;
    }

    public void deleteDraw(int id) {

        drawsStatsManager.deleteDrawStats(id);
        drawsTemplatesManager.deleteDrawsTemplates(id);

        drawsDao.deleteDraw(id);
    }

    public Draw getDraw(int id) {

        return getDraw(id, false, false, false);
    }

    public Draw getDraw(int id, boolean includePrizes, boolean includeStats, boolean includeTemplates) {

        final Draw draw = drawsDao.getDraw(id);

        loadDraw(draw, includePrizes, includeStats, includeTemplates);

        return draw;
    }

    public List<Draw> getDraws() {

        return getDraws(false, false, false);
    }

    public List<Draw> getDraws(boolean includePrizes, boolean includeStats, boolean includeTemplates) {

        return loadDraws(drawsDao.getDraws(), includePrizes, includeStats, includeTemplates);
    }

    public List<Draw> getDrawsUpToIncluding(int drawId) {

        return getDrawsUpToIncluding(drawId, false, false, false);
    }

    public List<Draw> getDrawsUpToIncluding(int drawId, boolean includePrizes, boolean includeStats, boolean includeTemplates) {

        return Lists.reverse(getDraws(includePrizes, includeStats, includeTemplates)).subList(0, drawId);
    }

    public List<Draw> getDrawsWithStarsAndNumbers() {

        return getDrawsWithStarsAndNumbers(false, false, false);
    }

    public List<Draw> getDrawsWithStarsAndNumbers(boolean includePrizes, boolean includeStats, boolean includeTemplates) {

        return loadDraws(drawsDao.getDrawsWithStarsAndNumbers(), includePrizes, includeStats, includeTemplates);
    }

    public List<Draw> getDrawsWithoutStarsOrNumbers() {

        return getDrawsWithoutStarsOrNumbers(false, false, false);
    }

    public List<Draw> getDrawsWithoutStarsOrNumbers(boolean includePrizes, boolean includeStats, boolean includeTemplates) {

        return loadDraws(drawsDao.getDrawsWithoutStarsOrNumbers(), includePrizes, includeStats, includeTemplates);
    }

    public Draw insertDraw(Draw draw) {

        drawsDao.insertDraw(draw);

        draw.getStars().forEach(star -> {

            drawsDao.insertDrawStar(draw.getId(), star);
        });

        draw.getNumbers().forEach(number -> {

            drawsDao.insertDrawNumber(draw.getId(), number);
        });

        return getDraw(draw.getId());
    }

    private List<Draw> loadDraws(List<Draw> draws, boolean includePrizes, boolean includeStats, boolean includeTemplates) {

        draws.forEach(draw -> loadDraw(draw, includePrizes, includeStats, includeTemplates));

        return draws;
    }

    private void loadDraw(Draw draw, boolean includePrizes, boolean includeStats, boolean includeTemplates) {

        draw.getStars().addAll(drawsDao.getDrawStars(draw.getId()));
        draw.getNumbers().addAll(drawsDao.getDrawNumbers(draw.getId()));

        if (includePrizes) {

            draw.getPrizes().addAll(drawsPrizesManager.getDrawPrizes(draw.getId()));
        }

        if (includeStats) {

            draw.getStarDrawStats().addAll(drawsStatsManager.getStarsDrawStats(draw.getId()));
            draw.getNumberDrawStats().addAll(drawsStatsManager.getNumbersDrawStats(draw.getId()));
        }

        if (includeTemplates) {

            draw.getStarsDrawsTemplates().addAll(drawsTemplatesManager.getStarsDrawsTemplates(draw.getId()));
            draw.getNumbersDrawsTemplates().addAll(drawsTemplatesManager.getNumbersDrawsTemplates(draw.getId()));
        }
    }

    public Draw updateDraw(Draw draw) {

        drawsDao.deleteDrawStars(draw.getId());
        drawsDao.deleteDrawNumbers(draw.getId());

        draw.getStars().forEach(star -> {

            drawsDao.insertDrawStar(draw.getId(), star);
        });

        draw.getNumbers().forEach(number -> {

            drawsDao.insertDrawNumber(draw.getId(), number);
        });

        return getDraw(draw.getId());
    }

    public Draw updateDrawUsingWebDriver(Draw draw) {

        final WebDriver webDriver = webDriverHelper.createChromeDriver();

        draw.getStars().clear();
        draw.getNumbers().clear();

        webDriver.get("https://www.euro-millions.com/results/" + SIMPLE_DATE_FORMAT.format(draw.getDate()));

        final WebElement webElementStarsAndNumbers = webDriver.findElement(By.id("jsBallOrderCell"));

        webElementStarsAndNumbers.findElements(By.className("lucky-star")).forEach(webElementStar -> {

            draw.getStars().add(Integer.parseInt(webElementStar.getText()));
        });

        webElementStarsAndNumbers.findElements(By.className("ball")).forEach(webElementNumber -> {

            draw.getNumbers().add(Integer.parseInt(webElementNumber.getText()));
        });

        draw.getPrizes().clear();

        final List<WebElement> webElementPrizes = webDriver.findElement(By.cssSelector(".table.breakdown")).findElements(By.tagName("tr"));

        draw.getPrizes().add(new DrawPrize(2, 5, Double.parseDouble(webElementPrizes.get(1).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(1, 5, Double.parseDouble(webElementPrizes.get(2).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(0, 5, Double.parseDouble(webElementPrizes.get(3).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(2, 4, Double.parseDouble(webElementPrizes.get(4).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(1, 4, Double.parseDouble(webElementPrizes.get(5).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(0, 4, Double.parseDouble(webElementPrizes.get(6).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(2, 3, Double.parseDouble(webElementPrizes.get(7).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(1, 3, Double.parseDouble(webElementPrizes.get(8).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(2, 2, Double.parseDouble(webElementPrizes.get(9).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(0, 3, Double.parseDouble(webElementPrizes.get(10).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(2, 1, Double.parseDouble(webElementPrizes.get(11).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        draw.getPrizes().add(new DrawPrize(1, 2, Double.parseDouble(webElementPrizes.get(12).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));

        if (draw.getStarsCount() > 10) {

            draw.getPrizes().add(new DrawPrize(0, 2, Double.parseDouble(webElementPrizes.get(13).findElements(By.tagName("td")).get(1).getText().replaceAll("[£,]", ""))));
        }

        webDriver.quit();

        updateDraw(draw);

        drawsPrizesManager.updateDrawPrizes(draw.getId(), draw.getPrizes());

        return draw;
    }
}
