package com.andremanuelbarbosa.euromillions.predictor.api;

import com.andremanuelbarbosa.euromillions.predictor.algorithms.Algorithm;
import com.andremanuelbarbosa.euromillions.predictor.manager.AlgorithmsManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Singleton
@Api("Algorithms")
@Path("/algorithms")
public class AlgorithmsApi extends AbstractApi {

    private final AlgorithmsManager algorithmsManager;

    @Inject
    public AlgorithmsApi(AlgorithmsManager algorithmsManager) {

        this.algorithmsManager = algorithmsManager;
    }

    @GET
    @ApiOperation("Retrieve Algorithms")
    public List<Algorithm> getAlgorithms() {

        return algorithmsManager.getAlgorithms();
    }

    @GET
    @Path("/{name}")
    @ApiOperation("Retrieve Algorithm with Name")
    public Algorithm getAlgorithm(@PathParam("name") String name) {

        return algorithmsManager.getAlgorithm(name);
    }
}
