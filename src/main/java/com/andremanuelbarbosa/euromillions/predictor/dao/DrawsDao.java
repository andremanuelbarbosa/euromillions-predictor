package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;

import java.util.List;
import java.util.SortedSet;

public interface DrawsDao extends Dao {

    Draw getDraw(int id);

    SortedSet<Integer> getDrawStars(int id);

    SortedSet<Integer> getDrawNumbers(int id);

    List<Draw> getDraws();

    void insertDraw(Draw draw);
}
