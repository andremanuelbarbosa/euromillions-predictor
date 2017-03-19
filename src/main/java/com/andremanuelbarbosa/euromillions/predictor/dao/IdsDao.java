package com.andremanuelbarbosa.euromillions.predictor.dao;

public interface IdsDao extends Dao {

    long getId(String name);

    void insertId(String name);

    void updateId(String name, long id);
}
