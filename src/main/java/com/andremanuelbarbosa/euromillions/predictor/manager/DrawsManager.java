package com.andremanuelbarbosa.euromillions.predictor.manager;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsDao;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class DrawsManager {

    private final DrawsDao drawsDao;

    @Inject
    public DrawsManager(DrawsDao drawsDao) {

        this.drawsDao = drawsDao;
    }

    public Draw getDraw(int id) {

        final Draw draw = drawsDao.getDraw(id);

        loadDraw(draw);

        return draw;
    }

    public List<Draw> getDraws() {

        final List<Draw> draws = drawsDao.getDraws();

        draws.forEach(draw -> loadDraw(draw));

        return draws;
    }

    private void loadDraw(Draw draw){

        draw.getStars().addAll(drawsDao.getDrawStars(draw.getId()));
        draw.getNumbers().addAll(drawsDao.getDrawNumbers(draw.getId()));
    }
}
