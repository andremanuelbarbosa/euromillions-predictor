package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;

import java.util.List;
import java.util.SortedSet;

public interface DrawsDao extends Dao {

    void deleteDraw(int id);

    void deleteDrawStars(int drawId);

    void deleteDrawNumbers(int drawId);

    Draw getDraw(int id);

    SortedSet<Integer> getDrawStars(int id);

    SortedSet<Integer> getDrawNumbers(int id);

    List<Draw> getDraws();

    List<Draw> getDrawsWithStarsAndNumbers();

    List<Draw> getDrawsWithoutStarsOrNumbers();

    void insertDraw(Draw draw);

    void insertDrawStar(int drawId, int star);

    void insertDrawNumber(int drawId, int number);

    void updateDraw(int id, Draw draw);
}
