package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import com.andremanuelbarbosa.euromillions.predictor.domain.DrawPrize;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrawPrizeSetMapper implements ResultSetMapper<DrawPrize> {

    @Override
    public DrawPrize map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return new DrawPrize(resultSet.getInt("stars"), resultSet.getInt("numbers"), resultSet.getDouble("prize"));
    }
}
