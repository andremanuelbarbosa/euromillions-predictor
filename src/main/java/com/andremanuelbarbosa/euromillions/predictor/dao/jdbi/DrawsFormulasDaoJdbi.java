package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsFormulasDao;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.DrawsFormulaSetMapper;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawFormula;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(DrawsFormulaSetMapper.class)
public interface DrawsFormulasDaoJdbi extends DrawsFormulasDao {

    String DRAWS_FORMULAS_COLUMNS = "draw_id, formula_name, stars_points, numbers_points";

    @Override
    @SqlUpdate("DELETE FROM draws_formulas WHERE draw_id = :drawId")
    void deleteDrawFormulas(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT " + DRAWS_FORMULAS_COLUMNS + " FROM draws_formulas WHERE draw_id = :drawId AND formula_name = :formulaName")
    DrawFormula getDrawFormula(@Bind("drawId") int drawId, @Bind("formulaName") String formulaName);

    @Override
    @SqlQuery("SELECT " + DRAWS_FORMULAS_COLUMNS + " FROM draws_formulas WHERE draw_id = :drawId")
    List<DrawFormula> getDrawFormulas(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("INSERT INTO draws_formulas ( " + DRAWS_FORMULAS_COLUMNS + " ) VALUES ( :drawId, :formulaName, :starsPoints, :numbersPoints )")
    void insertDrawFormula(@BindBean DrawFormula drawFormula);
}
