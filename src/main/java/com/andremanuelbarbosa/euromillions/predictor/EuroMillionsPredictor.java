package com.andremanuelbarbosa.euromillions.predictor;

import com.andremanuelbarbosa.euromillions.predictor.jobs.Cron;
import com.andremanuelbarbosa.euromillions.predictor.jobs.GuiceJobFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;
import java.util.Arrays;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.SUB_TYPES_SCANNER_INCLUDE_OBJECT_CLASS;

public class EuroMillionsPredictor extends Application<EuroMillionsPredictorConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EuroMillionsPredictor.class);

    private final EuroMillionsPredictorSwaggerBundle euroMillionsPredictorSwaggerBundle = new EuroMillionsPredictorSwaggerBundle();

    private Scheduler scheduler;

    private void addShutdownHook() {

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                LOGGER.info("Terminating the EuroMillions Predictor...");

                if (scheduler != null) {

                    try {

                        scheduler.shutdown();

                    } catch (SchedulerException e) {

                        LOGGER.error("Unable to terminate the Scheduler: {}.", e.getMessage());
                    }
                }

                LOGGER.info("The EuroMillions Predictor has terminated.");
            }
        });
    }

    @Override
    public void initialize(Bootstrap<EuroMillionsPredictorConfiguration> bootstrap) {

        bootstrap.addBundle(new MigrationsBundle<EuroMillionsPredictorConfiguration>() {

            @Override
            public DataSourceFactory getDataSourceFactory(EuroMillionsPredictorConfiguration euroMillionsPredictorConfiguration) {

                return euroMillionsPredictorConfiguration.getDataSourceFactory();
            }
        });

        bootstrap.addBundle(euroMillionsPredictorSwaggerBundle);

        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    private void initializeJobs(Injector injector) throws SchedulerException {

        scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.setJobFactory(new GuiceJobFactory(injector));

        scheduler.start();

        final String jobsPackageName = getClass().getPackage().getName() + ".jobs";

        (new Reflections(jobsPackageName, SUB_TYPES_SCANNER_INCLUDE_OBJECT_CLASS)).getSubTypesOf(Job.class).stream().forEach(jobClass -> {

            if (Arrays.asList(jobClass.getAnnotations()).stream().anyMatch(annotation -> annotation.annotationType() == Cron.class)) {

                final Cron cron = jobClass.getAnnotation(Cron.class);

                try {

                    scheduler.scheduleJob(JobBuilder.newJob(jobClass).build(),
                        TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cron.value())).build());

                } catch (SchedulerException e) {

                    throw new IllegalStateException(e);
                }
            }
        });
    }

    @Override
    public void run(EuroMillionsPredictorConfiguration euroMillionsPredictorConfiguration, Environment environment) throws Exception {

        final Injector injector = Guice.createInjector(new EuroMillionsPredictorModule(environment, euroMillionsPredictorConfiguration));

        initializeJobs(injector);

        (new Reflections(getClass().getPackage().getName() + ".api")).getTypesAnnotatedWith(Path.class).stream().forEach(apiClass -> {

            environment.jersey().register(injector.getInstance(apiClass));
        });

        addShutdownHook();
    }

    public static void main(String[] args) throws Exception {

        LOGGER.info("Starting the EuroMillions Predictor...");

        new EuroMillionsPredictor().run(args);

        LOGGER.info("The Odds EuroMillions has started.");
    }
}
