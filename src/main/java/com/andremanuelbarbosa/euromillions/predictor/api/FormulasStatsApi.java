package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import com.andremanuelbarbosa.euromillions.predictor.manager.FormulasStatsManager;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Api("Formulas Stats")
@Path("/formulas/stats")
public class FormulasStatsApi {

    private final FormulasStatsManager formulasStatsManager;

    @Inject
    public FormulasStatsApi(FormulasStatsManager formulasStatsManager) {

        this.formulasStatsManager = formulasStatsManager;
    }

    @GET
    @Path("/{drawId}")
    @ApiOperation("Retrieves the Formulas Stats for the Draw with ID")
    public List<FormulaStats> getFormulasStats(@PathParam("drawId") int drawId) {

        return formulasStatsManager.getFormulasStats(drawId);
    }

    @PUT
    @Path("/{drawId}")
    @ApiOperation("Updates the Formulas Stats for the Draw with ID")
    public Response updateFormulaStats(@PathParam("drawId") int drawId) {

        formulasStatsManager.updateFormulasStats(drawId);

        return Response.accepted().build();
    }
}
