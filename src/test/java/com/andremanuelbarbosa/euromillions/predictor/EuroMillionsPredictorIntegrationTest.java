package com.andremanuelbarbosa.euromillions.predictor;

import ch.qos.logback.classic.Level;
import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsManager;
import com.codahale.metrics.MetricRegistry;
import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.logging.DefaultLoggingFactory;
import io.dropwizard.logging.LoggingFactory;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class EuroMillionsPredictorIntegrationTest {

    private static final AtomicBoolean INITIALIZED = new AtomicBoolean(false);

    protected static DBI dbi;
    protected static Handle handle;
    protected static Injector injector;

    protected static DrawsManager drawsManager;

    protected static List<Draw> draws;
    protected static List<Draw> drawsReversed;

    protected static void initialize() {

        if (!INITIALIZED.getAndSet(true)) {

            final DataSourceFactory dataSourceFactory = new DataSourceFactory();
            dataSourceFactory.setDriverClass("org.postgresql.Driver");
            dataSourceFactory.setUser("postgres");
            dataSourceFactory.setPassword("postgres");
            dataSourceFactory.setUrl("jdbc:postgresql://localhost:5432/euromillions-predictor");

            final EuroMillionsPredictorConfiguration euroMillionsPredictorConfiguration = new EuroMillionsPredictorConfiguration();
            euroMillionsPredictorConfiguration.setDataSourceFactory(dataSourceFactory);

            final DefaultLoggingFactory defaultLoggingFactory = new DefaultLoggingFactory();
            defaultLoggingFactory.setLevel(Level.INFO);
            euroMillionsPredictorConfiguration.setLoggingFactory(defaultLoggingFactory);

            final Environment environment = new Environment("EuroMillionsPredictorIntegrationTest", Jackson.newObjectMapper(), null, new MetricRegistry(), null);

            final EuroMillionsPredictorModule euroMillionsPredictorModule = new EuroMillionsPredictorModule(environment, euroMillionsPredictorConfiguration);

            injector = Guice.createInjector(euroMillionsPredictorModule);

            dbi = (new DBIFactory()).build(environment, dataSourceFactory, "EuroMillionsPredictorIntegrationTest");

            handle = dbi.open();

            drawsManager = injector.getInstance(DrawsManager.class);

            draws = drawsManager.getDraws(true, true, true);
            drawsReversed = Lists.reverse(draws);
        }
    }
}
