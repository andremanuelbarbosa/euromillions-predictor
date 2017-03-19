package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.FormulaStats;
import com.andremanuelbarbosa.euromillions.predictor.manager.FormulasStatsManager;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    @Path("/{drawId}/{drawsCount}")
    @ApiOperation("Retrieves the Formula Stats for the Draw with ID and with the Draws Count")
    public FormulaStats getFormulaStats(@PathParam("drawId") int drawId, @PathParam("drawsCount") int drawsCount) {

        return formulasStatsManager.getFormulaStats(drawId, drawsCount);
    }

//    @GET
//    @ApiOperation("Retrieves the Formulas Stats")
//    public List<FormulaStats> getFormulasStats() {
//
//        return formulasStatsManager.getFormulasStats();
//    }

    @GET
    @Path("/{drawId}")
    @ApiOperation("Retrieves the Formulas Stats for the Draw with ID")
    public List<FormulaStats> getFormulasStats(@PathParam("drawId") int drawId) {

        return formulasStatsManager.getFormulasStats(drawId);
    }
}
