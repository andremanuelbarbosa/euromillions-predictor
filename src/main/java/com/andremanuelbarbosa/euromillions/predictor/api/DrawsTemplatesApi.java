package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsTemplatesManager;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@Singleton
@Api("Draws Templates")
@Path("/draws/{drawId}/templates")
public class DrawsTemplatesApi extends AbstractApi {

    private final DrawsTemplatesManager drawsTemplatesManager;

    @Inject
    public DrawsTemplatesApi(DrawsTemplatesManager drawsTemplatesManager) {

        this.drawsTemplatesManager = drawsTemplatesManager;
    }

    @DELETE
    @Path("/stars")
    @ApiOperation("Removes the Stars Templates for the Draw")
    public void deleteStarsDrawsTemplates(int drawId) {

        drawsTemplatesManager.deleteStarsDrawsTemplates(drawId);
    }

    @DELETE
    @Path("/numbers")
    @ApiOperation("Removes the Numbers Templates for the Draw")
    public void deleteNumbersDrawsTemplates(int drawId) {

        drawsTemplatesManager.deleteNumbersDrawsTemplates(drawId);
    }

    @GET
    @Path("/stars")
    @ApiOperation("Retrieves the Stars Templates for the Draw")
    public List<Set<Integer>> getStarsDrawsTemplates(@PathParam("drawId") int drawId) {

        return drawsTemplatesManager.getStarsDrawsTemplates(drawId);
    }

    @GET
    @Path("/numbers")
    @ApiOperation("Retrieves the Numbers Templates for the Draw")
    public List<Set<Integer>> getNumbersDrawsTemplates(@PathParam("drawId") int drawId) {

        return drawsTemplatesManager.getNumbersDrawsTemplates(drawId);
    }

    @PUT
    @Path("/stars")
    @ApiOperation("Updates the Stars Templates for the Draw")
    public Response updateStarsDrawsTemplates(@PathParam("drawId") int drawId) {

        drawsTemplatesManager.updateStarsDrawsTemplates(drawId);

        return Response.accepted().build();
    }

    @PUT
    @Path("/numbers")
    @ApiOperation("Updates the Numbers Templates for the Draw")
    public Response updateNumbersDrawsTemplates(@PathParam("drawId") int drawId) {

        drawsTemplatesManager.updateNumbersDrawsTemplates(drawId);

        return Response.accepted().build();
    }
}
