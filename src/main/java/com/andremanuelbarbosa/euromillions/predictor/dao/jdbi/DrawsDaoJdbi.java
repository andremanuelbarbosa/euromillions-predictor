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

    String DRAW_COLUMNS = "id, date, cost, prize, stars_count, numbers_count";

    @Override
    @SqlUpdate("DELETE FROM draws WHERE id = :id")
    void deleteDraw(@Bind("id") int drawId);

    @Override
    @SqlUpdate("DELETE FROM draws_stars WHERE draw_id = :drawId")
    void deleteDrawStars(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM draws_numbers WHERE draw_id = :drawId")
    void deleteDrawNumbers(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT " + DRAW_COLUMNS + " FROM draws WHERE id = :id")
    Draw getDraw(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT star FROM draws_stars WHERE draw_id = :drawId ORDER BY star ASC")
    SortedSet<Integer> getDrawStars(@Bind("drawId") int id);

    @Override
    @SqlQuery("SELECT number FROM draws_numbers WHERE draw_id = :drawId ORDER BY number ASC")
    SortedSet<Integer> getDrawNumbers(@Bind("drawId") int id);

    @Override
    @SqlQuery("SELECT " + DRAW_COLUMNS + " FROM draws ORDER BY id DESC")
    List<Draw> getDraws();

    @Override
    @SqlQuery("SELECT " + DRAW_COLUMNS + " FROM draws d WHERE ( SELECT COUNT(*) FROM draws_stars ds WHERE ds.draw_id = d.id ) = 2 AND ( SELECT COUNT(*) FROM draws_numbers dn WHERE dn.draw_id = d.id ) = 5 ORDER BY id DESC")
    List<Draw> getDrawsWithStarsAndNumbers();

    @Override
    @SqlQuery("SELECT " + DRAW_COLUMNS + " FROM draws d WHERE ( SELECT COUNT(*) FROM draws_stars ds WHERE ds.draw_id = d.id ) < 2 OR ( SELECT COUNT(*) FROM draws_numbers dn WHERE dn.draw_id = d.id ) < 5 ORDER BY id DESC")
    List<Draw> getDrawsWithoutStarsOrNumbers();

    @Override
    @SqlUpdate("INSERT INTO draws ( " + DRAW_COLUMNS + " ) VALUES ( :id, :date, :prize )")
    void insertDraw(@BindBean Draw draw);

    @Override
    @SqlUpdate("INSERT INTO draws_stars ( draw_id, star ) VALUES ( :drawId, :star )")
    void insertDrawStar(@Bind("drawId") int drawId, @Bind("star") int star);

    @Override
    @SqlUpdate("INSERT INTO draws_numbers ( draw_id, number ) VALUES ( :drawId, :number )")
    void insertDrawNumber(@Bind("drawId") int drawId, @Bind("number") int number);

    @Override
    @SqlUpdate("UPDATE draws SET date = :date, prize = :prize WHERE id = :_id")
    void updateDraw(@Bind("_id") int id, @BindBean Draw draw);
}
