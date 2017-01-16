package com.andremanuelbarbosa.euromillions.predictor.dao;

import java.util.List;
import java.util.Set;

public interface DrawsTemplatesDao extends Dao {

    void deleteStarsDrawsTemplates(int drawId);

    void deleteNumbersDrawsTemplates(int drawId);

    List<Integer> getDrawIdsWithoutTemplates();

    List<Set<Integer>> getStarsDrawsTemplates(int drawId);

    List<Set<Integer>> getNumbersDrawsTemplates(int drawId);

    void insertStarDrawsTemplate(int drawId, int template, int star);

    void insertNumberDrawsTemplate(int drawId, int template, int number);
}
