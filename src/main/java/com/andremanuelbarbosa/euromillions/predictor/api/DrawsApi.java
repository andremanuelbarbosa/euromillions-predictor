package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsManager;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Singleton
@Api("Draws")
@Path("/draws")
public class DrawsApi extends AbstractApi {

    private final DrawsManager drawsManager;

    @Inject
    public DrawsApi(DrawsManager drawsManager) {

        this.drawsManager = drawsManager;
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Retrieve Draw with ID")
    public Draw getDraw(@PathParam("id") int id) {

        return drawsManager.getDraw(id);
    }

    @GET
    @ApiOperation("Retrieve Draws")
    public List<Draw> getDraws() {

        return drawsManager.getDraws();
    }
}
