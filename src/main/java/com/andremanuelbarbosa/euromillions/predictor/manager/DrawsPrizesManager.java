package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsPrizesDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawPrize;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class DrawsPrizesManager {

    private final DrawsPrizesDao drawsPrizesDao;

    @Inject
    public DrawsPrizesManager(DrawsPrizesDao drawsPrizesDao) {

        this.drawsPrizesDao = drawsPrizesDao;
    }

    public void deleteDrawPrized(int drawId) {

        drawsPrizesDao.deleteDrawPrizes(drawId);
    }

    public List<DrawPrize> getDrawPrizes(int drawId) {

        return drawsPrizesDao.getDrawPrizes(drawId);
    }

    public List<DrawPrize> updateDrawPrizes(int drawId, List<DrawPrize> drawPrizes) {

        deleteDrawPrized(drawId);

        drawPrizes.forEach(drawPrize -> {

            drawsPrizesDao.insertDrawPrize(drawId, drawPrize);
        });

        return getDrawPrizes(drawId);
    }
}
