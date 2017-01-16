package com.andremanuelbarbosa.euromillions.predictor;

import com.andremanuelbarbosa.euromillions.predictor.domain.RealDraw;
import com.andremanuelbarbosa.euromillions.predictor.domain.RealDraws;
import com.codahale.metrics.MetricRegistry;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.Random;

public abstract class EuroMillionsPredictorIntegrationTest {

    protected static final List<RealDraw> REAL_DRAWS = RealDraws.getRealDraws();

    protected DBI dbi;
    protected Handle handle;
    protected Injector injector;

    protected void setUp() {

        if (injector == null) {

            final DataSourceFactory dataSourceFactory = new DataSourceFactory();
            dataSourceFactory.setDriverClass("org.postgresql.Driver");
            dataSourceFactory.setUser("postgres");
            dataSourceFactory.setPassword("postgres");
            dataSourceFactory.setUrl("jdbc:postgresql://localhost:5432/euromillions-predictor");

            final EuroMillionsPredictorConfiguration euroMillionsPredictorConfiguration = new EuroMillionsPredictorConfiguration();
            euroMillionsPredictorConfiguration.setDataSourceFactory(dataSourceFactory);

            final Environment environment = new Environment("EuroMillionsPredictorIntegrationTest", Jackson.newObjectMapper(), null, new MetricRegistry(), null);

            final EuroMillionsPredictorModule euroMillionsPredictorModule = new EuroMillionsPredictorModule(environment, euroMillionsPredictorConfiguration);

            injector = Guice.createInjector(euroMillionsPredictorModule);

            dbi = (new DBIFactory()).build(environment, dataSourceFactory, "EuroMillionsPredictorIntegrationTest");
        }

        handle = dbi.open();
    }

    protected void tearDown() {

        if (handle != null) {

            handle.close();
        }
    }
}
