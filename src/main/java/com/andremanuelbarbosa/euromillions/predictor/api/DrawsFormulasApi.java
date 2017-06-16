package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.DrawFormula;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsFormulasManager;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Singleton
@Api("Draws Formulas")
@Path("/draws/{drawId}/formulas")
public class DrawsFormulasApi extends AbstractApi {

    private final DrawsFormulasManager drawsFormulasManager;

    @Inject
    public DrawsFormulasApi(DrawsFormulasManager drawsFormulasManager) {

        this.drawsFormulasManager = drawsFormulasManager;
    }

    @GET
    @Path("/{formulaName}")
    @ApiOperation("Retrieves the Formula for the Draw")
    public DrawFormula getDrawFormula(@PathParam("drawId") int drawId, @PathParam("formulaName") String formulaName) {

        return drawsFormulasManager.getDrawFormula(drawId, formulaName);
    }

    @GET
    @ApiOperation("Retrieves the Formulas for the Draw")
    public List<DrawFormula> getDrawFormulas(@PathParam("drawId") int drawId) {

        return drawsFormulasManager.getDrawFormulas(drawId);
    }
}
