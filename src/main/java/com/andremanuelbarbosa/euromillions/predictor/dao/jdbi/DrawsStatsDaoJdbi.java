package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.DrawsStatsDao;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.NumberDrawStatsSetMapper;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.StarDrawStatsSetMapper;
import com.andremanuelbarbosa.euromillions.predictor.domain.NumberDrawStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.StarDrawStats;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

public interface DrawsStatsDaoJdbi extends DrawsStatsDao {

    String DRAWS_STATS_STARS_COLUMNS = "draw_id, star, freq, interval, relative_freq, average_interval";
    String DRAWS_STATS_NUMBERS_COLUMNS = "draw_id, number, freq, interval, relative_freq, average_interval";

    @Override
    @SqlUpdate("DELETE FROM draws_stats_stars WHERE draw_id = :drawId")
    void deleteStarsDrawStats(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM draws_stats_intervals_stars WHERE draw_id = :drawId")
    void deleteStarsDrawStatsIntervals(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM draws_stats_numbers WHERE draw_id = :drawId")
    void deleteNumbersDrawStats(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("DELETE FROM draws_stats_intervals_numbers WHERE draw_id = :drawId")
    void deleteNumberStarsDrawStatsIntervals(@Bind("drawId") int drawId);

    @Override
    @SqlQuery("SELECT d.id FROM draws d WHERE NOT EXISTS ( SELECT 1 FROM draws_stats_stars dss WHERE dss.draw_id = d.id ) AND NOT EXISTS ( SELECT 1 FROM draws_stats_numbers dsn WHERE dsn.draw_id = d.id ) ORDER BY d.id ASC")
    List<Integer> getDrawIdsWithoutStats();

    @Override
    @SqlQuery("SELECT interval FROM draws_stats_intervals_stars WHERE draw_id = :drawId AND star = :star ORDER BY id ASC")
    List<Integer> getStarDrawStatsIntervals(@Bind("drawId") int drawId, @Bind("star") int star);

    @Override
    @SqlQuery("SELECT interval FROM draws_stats_intervals_numbers WHERE draw_id = :drawId AND number = :number ORDER BY id ASC")
    List<Integer> getNumberDrawStatsIntervals(@Bind("drawId") int drawId, @Bind("number") int number);

    @Override
    @RegisterMapper(StarDrawStatsSetMapper.class)
    @SqlQuery("SELECT " + DRAWS_STATS_STARS_COLUMNS + " FROM draws_stats_stars WHERE draw_id = :drawId ORDER BY star ASC")
    List<StarDrawStats> getStarsDrawStats(@Bind("drawId") int drawId);

    @Override
    @RegisterMapper(NumberDrawStatsSetMapper.class)
    @SqlQuery("SELECT " + DRAWS_STATS_NUMBERS_COLUMNS + " FROM draws_stats_numbers WHERE draw_id = :drawId ORDER BY number ASC")
    List<NumberDrawStats> getNumbersDrawStats(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("INSERT INTO draws_stats_stars ( " + DRAWS_STATS_STARS_COLUMNS + " ) VALUES ( :drawId, :star, :freq, :interval, :relativeFreq, :averageInterval )")
    void insertStarDrawStats(@BindBean StarDrawStats starDrawStats);

    @Override
    @SqlUpdate("INSERT INTO draws_stats_numbers ( " + DRAWS_STATS_NUMBERS_COLUMNS + " ) VALUES ( :drawId, :number, :freq, :interval, :relativeFreq, :averageInterval )")
    void insertNumberDrawStats(@BindBean NumberDrawStats numberDrawStats);

    @Override
    @SqlUpdate("INSERT INTO draws_stats_intervals_stars ( draw_id, star, id, interval ) VALUES ( :drawId, :star, :id, :interval )")
    void insertStarDrawStatsInterval(@Bind("drawId") int drawId, @Bind("star") int star, @Bind("id") int id, @Bind("interval") int interval);

    @Override
    @SqlUpdate("INSERT INTO draws_stats_intervals_numbers ( draw_id, number, id, interval ) VALUES ( :drawId, :number, :id, :interval )")
    void insertNumberDrawStatsInterval(@Bind("drawId") int drawId, @Bind("number") int number, @Bind("id") int id, @Bind("interval") int interval);
}
