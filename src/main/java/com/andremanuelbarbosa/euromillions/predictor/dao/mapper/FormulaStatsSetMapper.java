package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FormulaStatsSetMapper implements ResultSetMapper<FormulaStats> {

    @Override
    public FormulaStats map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return new FormulaStats(resultSet.getInt("draw_id"), resultSet.getInt("draws_count"), resultSet.getInt("starting_draw_id"), resultSet.getInt("finishing_draw_id"),
            resultSet.getDouble("costs"), resultSet.getLong("execution_time"), resultSet.getString("maximum_points"), resultSet.getInt("maximum_wins"),
            resultSet.getDouble("maximum_wins_percentage"), resultSet.getDouble("maximum_earnings"), resultSet.getDouble("maximum_earnings_percentage"));
    }
}
