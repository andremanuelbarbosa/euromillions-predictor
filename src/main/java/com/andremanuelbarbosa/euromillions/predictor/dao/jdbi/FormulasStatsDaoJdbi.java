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

    String FORMULAS_STATS_COLUMNS = "draw_id, formula_name, costs, points, winnings, earnings, earnings_factor";

    @Override
    @SqlUpdate("DELETE FROM formulas_stats WHERE draw_id = :drawId")
    void deleteFormulaStats(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT id FROM draws d WHERE d.id > 1000 " +
        "AND ( SELECT COUNT(*) FROM formulas_stats fs WHERE fs.draw_id = d.id ) = 0 " +
        "AND ( SELECT COUNT(*) FROM draws_stars ds WHERE ds.draw_id = d.id ) > 0 " +
        "AND ( SELECT COUNT(*) FROM draws_numbers dn WHERE dn.draw_id = d.id ) > 0 ORDER BY d.id ASC")
    List<Integer> getDrawIdsWithoutFormulasStats();

    @Override
    @SqlQuery("SELECT " + FORMULAS_STATS_COLUMNS + " FROM formulas_stats " +
        "WHERE (:drawId = -1 OR draw_id = :drawId ) AND ( :formulaName = '' OR formula_name = :formulaName ) ")
    List<FormulaStats> getFormulasStats(@Bind("drawId") int drawId, @Bind("formulaName") String formulaName);

    @Override
    @RegisterMapper(FormulaStatsSetMapper.FormulaStatsFormulaSetMapper.class)
    @SqlQuery(
        "SELECT formula_name AS name, COUNT(*) AS draws, SUM(costs) AS costs, SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) AS wins, SUM(winnings) AS winnings, SUM(earnings) AS earnings, AVG(earnings_factor) AS earnings_factor, string_agg(points, ' | ' ORDER BY draw_id DESC) AS points " +
        "  FROM formulas_stats " +
        " WHERE ( :minDrawId = -1 OR draw_id >= :minDrawId ) AND ( :maxDrawId = -1 OR draw_id <= :maxDrawId )" +
        " GROUP BY formula_name " +
        "HAVING ( :minDrawId = -1 OR :maxDrawId = -1 OR COUNT(*) = (:maxDrawId - :minDrawId + 1) ) AND ( :minWins = -1 OR SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) >= :minWins ) AND ( :minEarningsFactor = -1 OR AVG(earnings_factor) >= :minEarningsFactor ) " +
        " ORDER BY SUM(CASE WHEN winnings > 0 THEN 1 ELSE 0 END) DESC, AVG(earnings_factor) DESC " +
        " LIMIT :count")
    List<FormulaStats.Formula> getFormulasStatsFormulas(@Bind("minDrawId") int minDrawId, @Bind("maxDrawId") int maxDrawId, @Bind("minWins") int minWins, @Bind("minEarningsFactor") double minEarningsFactor, @Bind("count") int count);

    @Override
    @SqlUpdate("INSERT INTO formulas_stats ( " + FORMULAS_STATS_COLUMNS + " ) " +
        "VALUES ( :drawId, :formulaName, :costs, :points, :winnings, :earnings, :earningsFactor )")
    void insertFormulaStats(@BindBean FormulaStats formulaStats);
}
