package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.BetStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.manager.BetsManager;
import com.andremanuelbarbosa.euromillions.predictor.manager.BetsStatsManager;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsManager;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.*;
import java.util.List;

@Singleton
@Api("Draws")
@Path("/draws")
public class DrawsApi extends AbstractApi {

    private final BetsManager betsManager;
    private final DrawsManager drawsManager;
    private final BetsStatsManager betsStatsManager;

    @Inject
    public DrawsApi(BetsManager betsManager, DrawsManager drawsManager, BetsStatsManager betsStatsManager) {

        this.betsManager = betsManager;
        this.drawsManager = drawsManager;
        this.betsStatsManager = betsStatsManager;
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Removes the Draw with ID")
    public void deleteDraw(@PathParam("id") int id) {

        drawsManager.deleteDraw(id);
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Retrieves the Draw with ID")
    public Draw getDraw(@PathParam("id") int id) {

        return drawsManager.getDraw(id);
    }

    @GET
    @Path("/{id}/bets")
    @ApiOperation("Retrieves the Bets for the Draw with ID")
    public List<Bet> getDrawBets(@PathParam("id") int id) {

        return betsManager.getBets(id);
    }

    @GET
    @Path("/{id}/bets/stats")
    @ApiOperation("Retrieves the Bets for the Draw with ID")
    public BetStats getDrawBetsStats(@PathParam("id") int id) {

        return betsStatsManager.getBetStats(betsManager.getBets(id));
    }

    @GET
    @ApiOperation("Retrieves the Draws")
    public List<Draw> getDraws() {

        return drawsManager.getDraws();
    }

    @POST
    @ApiOperation("Creates a Draw")
    public Draw insertDraw(Draw draw) {

        return drawsManager.insertDraw(draw);
    }

    @PUT
    @Path("/{id}")
    @ApiOperation("Updates the Draw with ID")
    public Draw updateDraw(@PathParam("id") int id, Draw draw) {

        return drawsManager.updateDraw(draw);
    }
}
