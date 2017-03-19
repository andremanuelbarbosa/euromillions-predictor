package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.domain.Bet;
import com.andremanuelbarbosa.euromillions.predictor.domain.Formula;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsManager;
import com.andremanuelbarbosa.euromillions.predictor.manager.FormulasManager;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Singleton
@Api("Formulas")
@Path("/formulas")
public class FormulasApi extends AbstractApi {

    private final DrawsManager drawsManager;
    private final FormulasManager formulasManager;

    @Inject
    public FormulasApi(DrawsManager drawsManager, FormulasManager formulasManager) {

        this.drawsManager = drawsManager;
        this.formulasManager = formulasManager;
    }

    @GET
    @ApiOperation("Retrieve Formulas")
    public List<Formula> getFormulas() {

        return formulasManager.getFormulas();
    }

    @GET
    @Path("/{name}")
    @ApiOperation("Retrieve Formula with Name")
    public Formula getFormula(@PathParam("name") String name) {

        return formulasManager.getFormula(name);
    }

    @GET
    @Path("/{name}/bet")
    @ApiOperation("Retrieve the next Bet for the Formula with Name")
    public Bet getFormulaBet(@PathParam("name") String name) {

        return formulasManager.getFormula(name).getNextBet(Lists.reverse(drawsManager.getDraws(true, true, true)));
    }
}
