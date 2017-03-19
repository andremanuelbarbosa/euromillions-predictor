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

public interface FormulasStatsDaoJdbi extends FormulasStatsDao {

    String FORMULAS_STATS_COLUMNS = "draw_id, draws_count, starting_draw_id, finishing_draw_id, costs, execution_time, maximum_points, maximum_wins, maximum_wins_percentage, maximum_earnings, maximum_earnings_percentage";
    String FORMULAS_STATS_FORMULAS_COLUMNS = "draw_id, draws_count, formula_name, maximum_points, wins, wins_percentage, earnings, earnings_percentage, balance";

    @Override
    @SqlUpdate("DELETE FROM formulas_stats WHERE draw_id = :drawId")
    void deleteFormulaStats(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats WHERE draw_id = :drawId AND draws_count = :drawsCount")
    void deleteFormulaStats(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas WHERE draw_id = :drawId")
    void deleteFormulaStatsFormulas(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas WHERE draw_id = :drawId AND draws_count = :drawsCount")
    void deleteFormulaStatsFormulas(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas_stars WHERE draw_id = :drawId")
    void deleteFormulaStatsFormulaStars(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas_stars WHERE draw_id = :drawId AND draws_count = :drawsCount")
    void deleteFormulaStatsFormulaStars(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas_stars WHERE draw_id = :drawId AND draws_count = :drawsCount AND formula_name = :formulaName")
    void deleteFormulaStatsFormulaStars(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @Bind("formulaName") String formulaName);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas_numbers WHERE draw_id = :drawId")
    void deleteFormulaStatsFormulaNumbers(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas_numbers WHERE draw_id = :drawId AND draws_count = :drawsCount")
    void deleteFormulaStatsFormulaNumbers(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @SqlUpdate("DELETE FROM formulas_stats_formulas_numbers WHERE draw_id = :drawId AND draws_count = :drawsCount AND formula_name = :formulaName")
    void deleteFormulaStatsFormulaNumbers(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @Bind("formulaName") String formulaName);

    @Override
    @SqlQuery("SELECT id FROM draws d WHERE d.id > 100 AND ( SELECT COUNT(*) FROM formulas_stats fs WHERE fs.draw_id = d.id ) < 5 AND ( SELECT COUNT(*) FROM draws_stars ds WHERE ds.draw_id = d.id ) > 0 AND ( SELECT COUNT(*) FROM draws_numbers dn WHERE dn.draw_id = d.id ) > 0 ORDER BY d.id ASC")
    List<Integer> getDrawIdsWithoutFormulasStats();

    @Override
    @SqlQuery("SELECT formula_name FROM formulas_stats_formulas WHERE draw_id = :drawId AND draws_count = :drawsCount AND earnings = :earnings")
    List<String> getFormulaNamesWithEarnings(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @Bind("earnings") double earnings);

    @Override
    @SqlQuery("SELECT formula_name FROM formulas_stats_formulas WHERE draw_id = :drawId AND draws_count = :drawsCount AND maximum_points = :maximumPoints")
    List<String> getFormulaNamesWithMaximumPoints(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @Bind("maximumPoints") String maximumPoints);

    @Override
    @SqlQuery("SELECT formula_name FROM formulas_stats_formulas WHERE draw_id = :drawId AND draws_count = :drawsCount AND wins = :wins")
    List<String> getFormulaNamesWithWins(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @Bind("wins") int wins);

    @Override
    @RegisterMapper(FormulaStatsSetMapper.class)
    @SqlQuery("SELECT " + FORMULAS_STATS_COLUMNS + " FROM formulas_stats WHERE draw_id = :drawId AND draws_count = :drawsCount")
    FormulaStats getFormulaStats(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @RegisterMapper(FormulaStatsSetMapper.class)
    @SqlQuery("SELECT " + FORMULAS_STATS_COLUMNS + " FROM formulas_stats ORDER BY draw_id DESC, draws_count ASC")
    List<FormulaStats> getFormulasStats();

    @Override
    @RegisterMapper(FormulaStatsSetMapper.class)
    @SqlQuery("SELECT " + FORMULAS_STATS_COLUMNS + " FROM formulas_stats WHERE draw_id = :drawId ORDER BY draws_count ASC")
    List<FormulaStats> getFormulasStats(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT MAX(earnings) FROM formulas_stats_formulas WHERE draw_id = :drawId AND draws_count = :drawsCount")
    double getMaximumEarnings(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @SqlQuery("SELECT MAX(maximum_points) FROM formulas_stats_formulas WHERE draw_id = :drawId AND draws_count = :drawsCount")
    String getMaximumPoints(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @SqlQuery("SELECT MAX(wins) FROM formulas_stats_formulas WHERE draw_id = :drawId AND draws_count = :drawsCount")
    int getMaximumWins(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount);

    @Override
    @SqlUpdate("INSERT INTO formulas_stats ( " + FORMULAS_STATS_COLUMNS + " ) " +
        "VALUES ( :drawId, :drawsCount, :startingDrawId, :finishingDrawId, :costs, :executionTime, :maximumPoints, :maximumWins, :maximumWinsPercentage, :maximumEarnings, :maximumEarningsPercentage )")
    void insertFormulaStats(@BindBean FormulaStats formulaStats);

    @Override
    @SqlUpdate("INSERT INTO formulas_stats_formulas ( " + FORMULAS_STATS_FORMULAS_COLUMNS + " ) " +
        "VALUES ( :drawId, :drawsCount, :formulaName, :maximumPoints, :wins, :winsPercentage, :earnings, :earningsPercentage, :balance )")
    void insertFormulaStatsFormula(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @BindBean FormulaStats.Formula formulaStatsFormula);

    @Override
    @SqlUpdate("INSERT INTO formulas_stats_formulas_stars ( draw_id, draws_count, formula_name, star, freq ) VALUES ( :drawId, :drawsCount, :formulaName, :star, :freq )")
    void insertFormulaStatsFormulaStars(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @Bind("formulaName") String formulaName, @Bind("star") int number, @Bind("freq") int freq);

    @Override
    @SqlUpdate("INSERT INTO formulas_stats_formulas_numbers ( draw_id, draws_count, formula_name, number, freq ) VALUES ( :drawId, :drawsCount, :formulaName, :number, :freq )")
    void insertFormulaStatsFormulaNumbers(@Bind("drawId") int drawId, @Bind("drawsCount") int drawsCount, @Bind("formulaName") String formulaName, @Bind("number") int numbers, @Bind("freq") int freq);

    @Override
    @SqlUpdate("UPDATE formulas_stats SET starting_draw_id = :startingDrawId, finishing_draw_id = :finishingDrawId, costs = :costs, execution_time = :executionTime, maximum_points = :maximumPoints, " +
        "maximum_wins = :maximumWins, maximum_wins_percentage = :maximumWinsPercentage, maximum_earnings = :maximumEarnings, maximum_earnings_percentage = :maximumEarningsPercentage " +
        "WHERE draw_id = :drawId AND draws_count = :drawsCount")
    void updateFormulaStats(@BindBean FormulaStats formulaStats);
}
