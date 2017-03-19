package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.DrawPrize;

import java.util.List;

public interface DrawsPrizesDao extends Dao {

    void deleteDrawPrizes(int drawId);

    List<DrawPrize> getDrawPrizes(int drawId);

    void insertDrawPrize(int drawId, DrawPrize drawPrize);
}
