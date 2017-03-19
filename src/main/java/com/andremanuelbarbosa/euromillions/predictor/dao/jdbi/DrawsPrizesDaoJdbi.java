package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsPrizesDao;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.DrawPrizeSetMapper;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawPrize;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(DrawPrizeSetMapper.class)
public interface DrawsPrizesDaoJdbi extends DrawsPrizesDao {

    String DRAWS_PRIZES_COLUMNS = "draw_id, stars, numbers, prize";

    @Override
    @SqlUpdate("DELETE FROM draws_prizes WHERE draw_id = :drawId")
    void deleteDrawPrizes(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT " + DRAWS_PRIZES_COLUMNS + " FROM draws_prizes WHERE draw_id = :drawId ORDER BY prize DESC")
    List<DrawPrize> getDrawPrizes(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("INSERT INTO draws_prizes ( " + DRAWS_PRIZES_COLUMNS + " ) VALUES ( :drawId, :stars, :numbers, :prize )")
    void insertDrawPrize(@Bind("drawId") int drawId, @BindBean DrawPrize drawPrize);
}
