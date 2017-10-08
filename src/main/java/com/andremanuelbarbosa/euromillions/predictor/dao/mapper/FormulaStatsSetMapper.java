package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FormulaStatsSetMapper implements ResultSetMapper<FormulaStats> {

    @Override
    public FormulaStats map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return new FormulaStats(resultSet.getInt("draw_id"), resultSet.getString("formula_name"), resultSet.getDouble("costs"),
            resultSet.getString("points"), resultSet.getDouble("winnings"), resultSet.getDouble("earnings"), resultSet.getDouble("earnings_percentage"));
    }
}
