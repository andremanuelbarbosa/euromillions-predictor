package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class DrawsManager {

    private final DrawsDao drawsDao;
    private final DrawsStatsManager drawsStatsManager;
    private final DrawsPrizesManager drawsPrizesManager;
    private final DrawsTemplatesManager drawsTemplatesManager;

    @Inject
    public DrawsManager(DrawsDao drawsDao, DrawsStatsManager drawsStatsManager, DrawsPrizesManager drawsPrizesManager, DrawsTemplatesManager drawsTemplatesManager) {

        this.drawsDao = drawsDao;
        this.drawsStatsManager = drawsStatsManager;
        this.drawsPrizesManager = drawsPrizesManager;
        this.drawsTemplatesManager = drawsTemplatesManager;
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
}
