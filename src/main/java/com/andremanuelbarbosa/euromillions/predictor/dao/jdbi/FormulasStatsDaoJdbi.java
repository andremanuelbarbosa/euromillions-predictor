package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.FormulasStatsDao;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.FormulaStatsSetMapper;
import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(FormulaStatsSetMapper.class)
public interface FormulasStatsDaoJdbi extends FormulasStatsDao {

    String FORMULAS_STATS_COLUMNS = "draw_id, formula_name, costs, points, winnings, earnings, earnings_percentage";

    @Override
    @SqlUpdate("DELETE FROM formulas_stats WHERE draw_id = :drawId")
    void deleteFormulaStats(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT id FROM draws d WHERE d.id > 100 AND ( SELECT COUNT(*) FROM formulas_stats fs WHERE fs.draw_id = d.id ) < 5 AND ( SELECT COUNT(*) FROM draws_stars ds WHERE ds.draw_id = d.id ) > 0 AND ( SELECT COUNT(*) FROM draws_numbers dn WHERE dn.draw_id = d.id ) > 0 ORDER BY d.id ASC")
    List<Integer> getDrawIdsWithoutFormulasStats();

    @Override
    @RegisterMapper(FormulaStatsSetMapper.class)
    @SqlQuery("SELECT " + FORMULAS_STATS_COLUMNS + " FROM formulas_stats ORDER BY draw_id DESC")
    List<FormulaStats> getFormulasStats();

    @Override
    @RegisterMapper(FormulaStatsSetMapper.class)
    @SqlQuery("SELECT " + FORMULAS_STATS_COLUMNS + " FROM formulas_stats WHERE draw_id = :drawId")
    List<FormulaStats> getFormulasStats(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("INSERT INTO formulas_stats ( " + FORMULAS_STATS_COLUMNS + " ) " +
        "VALUES ( :drawId, :formulaName, :costs, :points, :winnings, :earnings, :earningsPercentage )")
    void insertFormulaStats(@BindBean FormulaStats formulaStats);
}
