package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BetSetMapper implements ResultSetMapper<Bet> {

    @Override
    public Bet map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return new Bet(resultSet.getLong("id"), resultSet.getInt("draw_id"), resultSet.getString("formula_name"));
    }
}
