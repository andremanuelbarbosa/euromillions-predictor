package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsDao;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.DrawSetMapper;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;
import java.util.SortedSet;

@RegisterMapper(DrawSetMapper.class)
public interface DrawsDaoJdbi extends DrawsDao {

    @Override
    @SqlQuery("SELECT id, date, prize FROM draws WHERE id = :id")
    Draw getDraw(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT star FROM draws_stars WHERE draw_id = :drawId ORDER BY star ASC")
    SortedSet<Integer> getDrawStars(@Bind("drawId") int id);

    @Override
    @SqlQuery("SELECT number FROM draws_numbers WHERE draw_id = :drawId ORDER BY number ASC")
    SortedSet<Integer> getDrawNumbers(@Bind("drawId") int id);

    @Override
    @SqlQuery("SELECT id, date, prize FROM draws ORDER BY id DESC")
    List<Draw> getDraws();

    @Override
    @SqlUpdate("INSERT INTO draws ( id, date, prize ) VALUES ( :id, :date, :prize )")
    void insertDraw(@BindBean Draw draw);
}
