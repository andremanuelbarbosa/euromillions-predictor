package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import com.andremanuelbarbosa.euromillions.predictor.domain.StarDrawStats;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StarDrawStatsSetMapper implements ResultSetMapper<StarDrawStats> {

    @Override
    public StarDrawStats map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return new StarDrawStats(resultSet.getInt("draw_id"), resultSet.getInt("freq"), resultSet.getInt("interval"),
            resultSet.getDouble("relative_freq"), resultSet.getDouble("average_interval"), resultSet.getInt("star"));
    }
}
