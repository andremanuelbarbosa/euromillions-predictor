package com.andremanuelbarbosa.euromillions.predictor.dao.mapper;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class DrawTemplateSetMapper implements ResultSetMapper<Set<Integer>> {

    @Override
    public Set<Integer> map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {

        return Arrays.asList(resultSet.getString("items").split(",")).stream().map(Integer::parseInt).collect(Collectors.toSet());
    }
}
