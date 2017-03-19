package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.dao.BetsDao;
import com.andremanuelbarbosa.euromillions.predictor.dao.mapper.BetSetMapper;
import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;
import java.util.SortedSet;

@RegisterMapper(BetSetMapper.class)
public interface BetsDaoJdbi extends BetsDao {

    String BET_COLUMNS = "id, draw_id, formula_name";

    @Override
    @SqlUpdate("DELETE FROM bets WHERE id = :id")
    void deleteBet(@Bind("id") long id);

    @Override
    @SqlUpdate("DELETE FROM bets_stars WHERE bet_id = :betId")
    void deleteBetStars(@Bind("betId") long betId);

    @Override
    @SqlUpdate("DELETE FROM bets_numbers WHERE bet_id = :betId")
    void deleteBetNumbers(@Bind("betId") long betId);

    @Override
    @SqlQuery("SELECT " + BET_COLUMNS + " FROM bets WHERE id = :id")
    Bet getBet(@Bind("id") long id);

    @Override
    @SqlQuery("SELECT star FROM bets_stars WHERE bet_id = :betId ORDER BY star ASC")
    SortedSet<Integer> getBetStars(@Bind("betId") long betId);

    @Override
    @SqlQuery("SELECT number FROM bets_numbers WHERE bet_id = :betId ORDER BY number ASC")
    SortedSet<Integer> getBetNumbers(@Bind("betId") long betId);

    @Override
    @SqlQuery("SELECT " + BET_COLUMNS + " FROM bets ORDER BY draw_id DESC, id ASC")
    List<Bet> getBets();

    @Override
    @SqlQuery("SELECT " + BET_COLUMNS + " FROM bets WHERE draw_id = :drawId ORDER BY id ASC")
    List<Bet> getBets(@Bind("drawId") int drawId);

    @Override
    @SqlUpdate("INSERT INTO bets ( " + BET_COLUMNS + " ) VALUES ( :_id, :drawId, :formulaName)")
    void insertBet(@Bind("_id") long id, @BindBean Bet bet);

    @Override
    @SqlUpdate("INSERT INTO bets_stars ( bet_id, star ) VALUES ( :betId, :star)")
    void insertBetStar(@Bind("betId") long betId, @Bind("star") int star);

    @Override
    @SqlUpdate("INSERT INTO bets_numbers ( bet_id, number ) VALUES ( :betId, :number)")
    void insertBetNumber(@Bind("betId") long betId, @Bind("number") int number);

    @Override
    @SqlUpdate("UPDATE bets SET draw_id = :drawId, formula_name = :formulaName WHERE id = :id")
    void updateBet(@BindBean Bet bet);
}
