package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import com.andremanuelbarbosa.euromillions.predictor.domain.DrawFormula;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrawsFormulaSetMapper implements ResultSetMapper<DrawFormula> {

    @Override
    public DrawFormula map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return new DrawFormula(resultSet.getInt("draw_id"), resultSet.getString("formula_name"), resultSet.getInt("starsPoints"), resultSet.getInt("numbersPoints"));
    }
}
