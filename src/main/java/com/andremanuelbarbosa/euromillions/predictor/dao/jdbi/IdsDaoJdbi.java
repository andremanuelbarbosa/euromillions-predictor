package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.IdsDao;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface IdsDaoJdbi extends IdsDao {

    @Override
    @SqlQuery("SELECT id FROM ids WHERE NAME = :name")
    long getId(@Bind("name") String name);

    @Override
    @SqlUpdate("INSERT INTO ids ( name, id ) VALUES ( :name, 1 )")
    void insertId(@Bind("name") String name);

    @Override
    @SqlUpdate("UPDATE ids SET id = :id WHERE name = :name")
    void updateId(@Bind("name") String name, @Bind("id") long id);
}
