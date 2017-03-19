package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.BetStats;
import com.andremanuelbarbosa.euromillions.predictor.manager.BetsManager;
import com.andremanuelbarbosa.euromillions.predictor.manager.BetsStatsManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import java.util.List;

@Singleton
@Api("Bets")
@Path("/bets")
public class BetsApi extends AbstractApi {

    private final BetsManager betsManager;
    private final BetsStatsManager betsStatsManager;

    @Inject
    public BetsApi(BetsManager betsManager, BetsStatsManager betsStatsManager) {

        this.betsManager = betsManager;
        this.betsStatsManager = betsStatsManager;
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Removes the Bet with ID")
    public void deleteBet(@PathParam("id") long id) {

        betsManager.deleteBet(id);
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Retrieves the Bet with ID")
    public Bet getBet(@PathParam("id") long id) {

        return betsManager.getBet(id);
    }

    @GET
    @Path("/{id}/stats")
    @ApiOperation("Retrieves the Stats for the Bet with ID")
    public BetStats getBetStats(@PathParam("id") long id) {

        return betsStatsManager.getBetStats(betsManager.getBet(id));
    }

    @GET
    @ApiOperation("Retrieves the Bets")
    public List<Bet> getBets() {

        return betsManager.getBets();
    }

    @GET
    @Path("/stats")
    @ApiOperation("Retrieves the Bets Stats")
    public BetStats getBetsStats() {

        return betsStatsManager.getBetStats(betsManager.getBets());
    }

    @POST
    @ApiOperation("Creates a Bet")
    public Bet insertBet(Bet bet) {

        return betsManager.saveBet(bet);
    }

    @PUT
    @Path("/{id}")
    @ApiOperation("Updates the Bet with ID")
    public Bet updateBet(@PathParam("id") long id, Bet bet) {

        return betsManager.saveBet(bet);
    }
}
