package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrawSetMapper implements ResultSetMapper<Draw> {

    @Override
    public Draw map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return new Draw(resultSet.getInt("id"), resultSet.getTimestamp("date"), resultSet.getDouble("prize"));
    }
}
