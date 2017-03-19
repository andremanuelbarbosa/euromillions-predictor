package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.domain.DrawStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.NumberDrawStats;
import com.andremanuelbarbosa.euromillions.predictor.domain.StarDrawStats;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsManager;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsStatsManager;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Api("Draws Stats")
@Path("/draws/{drawId}/stats")
public class DrawsStatsApi extends AbstractApi {

    private final DrawsManager drawsManager;
    private final DrawsStatsManager drawsStatsManager;

    @Inject
    public DrawsStatsApi(DrawsManager drawsManager, DrawsStatsManager drawsStatsManager) {

        this.drawsManager = drawsManager;
        this.drawsStatsManager = drawsStatsManager;
    }

    @DELETE
    @ApiOperation("Removes the Stats for the Draw")
    public Response deleteDrawStats(int drawId) {

        drawsStatsManager.deleteDrawStats(drawId);

        return Response.noContent().build();
    }

    @DELETE
    @Path("/stars")
    @ApiOperation("Removes the Stars Stats for the Draw")
    public Response deleteStarsDrawStats(int drawId) {

        drawsStatsManager.deleteStarsDrawStats(drawId);

        return Response.noContent().build();
    }

    @DELETE
    @Path("/numbers")
    @ApiOperation("Removes the Numbers Stats for the Draw")
    public Response deleteNumbersDrawStats(int drawId) {

        drawsStatsManager.deleteNumbesDrawStats(drawId);

        return Response.noContent().build();
    }

    @GET
    @ApiOperation("Retrieves the Stats for the Draw")
    public List<DrawStats> getDrawStats(@PathParam("drawId") int drawId) {

        return drawsStatsManager.getDrawStats(drawId);
    }

    @GET
    @Path("/stars")
    @ApiOperation("Retrieves the Stars Stats for the Draw")
    public List<StarDrawStats> getStarsDrawStats(@PathParam("drawId") int drawId) {

        return drawsStatsManager.getStarsDrawStats(drawId);
    }

    @GET
    @Path("/numbers")
    @ApiOperation("Retrieves the Numbers Stats for the Draw")
    public List<NumberDrawStats> getNumberDrawStats(@PathParam("drawId") int drawId) {

        return drawsStatsManager.getNumbersDrawStats(drawId);
    }

    @PUT
    @ApiOperation("Updates the Stats for the Draw")
    public List<DrawStats> updateDrawStats(@PathParam("drawId") int drawId) {

        return drawsStatsManager.updateDrawStats(drawId, drawsManager.getDrawsUpToIncluding(drawId));
    }

    @PUT
    @Path("/stars")
    @ApiOperation("Updates the Stars Stats for the Draw")
    public List<StarDrawStats> updateStarsDrawStats(@PathParam("drawId") int drawId) {

        return drawsStatsManager.updateStarsDrawStats(drawId, drawsManager.getDrawsUpToIncluding(drawId));
    }

    @PUT
    @Path("/numbers")
    @ApiOperation("Updates the Numbers Stats for the Draw")
    public List<NumberDrawStats> updateNumbersDrawStats(@PathParam("drawId") int drawId) {

        return drawsStatsManager.updateNumbersDrawStats(drawId, drawsManager.getDrawsUpToIncluding(drawId));
    }
}
