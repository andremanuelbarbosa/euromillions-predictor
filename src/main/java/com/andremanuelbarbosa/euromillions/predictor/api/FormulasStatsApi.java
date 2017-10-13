package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import com.andremanuelbarbosa.euromillions.predictor.manager.FormulasStatsManager;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.inject.Singleton;
import javax.ws.rs.*;
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

    @PUT
    @Path("/{drawId}")
    @ApiOperation("Updates the Formulas Stats for the Draw with ID")
    public Response updateFormulaStats(@PathParam("drawId") int drawId) {

        formulasStatsManager.updateFormulasStats(drawId);

        return Response.accepted().build();
    }

    @GET
    @ApiOperation("Retrieves the Formulas Stats")
    public List<FormulaStats> getFormulasStats(@QueryParam("drawId") int drawId, @QueryParam("formulaName") String formulaName) {

        return formulasStatsManager.getFormulasStats(drawId, formulaName);
    }

    @GET
    @Path("/formulas")
    @ApiOperation("Retrieves the Formulas Stats Formulas")
    public List<FormulaStats.Formula> getFormulasStatsFormulas(
        @QueryParam("minDrawId") @ApiParam("The ID of the Draw from which the Formula Stats will be considered") Integer minDrawId,
        @QueryParam("maxDrawId") @ApiParam("The ID of the Draw until which the Formula Stats will be considered") Integer maxDrawId,
        @QueryParam("minWins") @ApiParam("The minimum number of combined Wins") @DefaultValue("1") Integer minWins,
        @QueryParam("minEarningsFactor") @ApiParam("The minimum average Earnings Factor") @DefaultValue("0.01") Double minEarningsFactor,
        @QueryParam("count") @ApiParam("The maximum number of Formula Stats to be returned") @DefaultValue("100") Integer count) {

        return formulasStatsManager.getFormulasStatsFormulas(minDrawId, maxDrawId, minWins, minEarningsFactor, count);
    }
}
