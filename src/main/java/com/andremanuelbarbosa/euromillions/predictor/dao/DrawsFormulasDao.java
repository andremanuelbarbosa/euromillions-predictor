package com.andremanuelbarbosa.euromillions.predictor.dao;

import com.andremanuelbarbosa.euromillions.predictor.domain.DrawFormula;

import java.util.List;

public interface DrawsFormulasDao extends Dao {

    void deleteDrawFormulas(int drawId);

    DrawFormula getDrawFormula(int drawId, String formulaName);

    List<DrawFormula> getDrawFormulas(int drawId);

    void insertDrawFormula(DrawFormula drawFormula);
}
