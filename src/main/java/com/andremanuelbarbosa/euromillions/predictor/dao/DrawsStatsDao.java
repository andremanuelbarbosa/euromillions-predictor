package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.NumberDrawStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.StarDrawStats;

import java.util.List;

public interface DrawsStatsDao extends Dao {

    void deleteStarsDrawStats(int drawId);

    void deleteStarsDrawStatsIntervals(int drawId);

    void deleteNumbersDrawStats(int drawId);

    void deleteNumberStarsDrawStatsIntervals(int drawId);

    List<Integer> getDrawIdsWithoutStats();

    List<Integer> getStarDrawStatsIntervals(int drawId, int star);

    List<Integer> getNumberDrawStatsIntervals(int drawId, int number);

    List<StarDrawStats> getStarsDrawStats(int drawId);

    List<NumberDrawStats> getNumbersDrawStats(int drawId);

    void insertStarDrawStats(StarDrawStats starDrawStats);

    void insertNumberDrawStats(NumberDrawStats numberDrawStats);

    void insertStarDrawStatsInterval(int drawId, int star, int id, int interval);

    void insertNumberDrawStatsInterval(int drawId, int number, int id, int interval);
}
