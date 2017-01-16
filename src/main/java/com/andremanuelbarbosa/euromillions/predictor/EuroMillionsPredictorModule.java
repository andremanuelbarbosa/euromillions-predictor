package com.andremanuelbarbosa.euromillions.predictor;

import com.andremanuelbarbosa.euromillions.predictor.dao.Dao;
import com.google.inject.Binder;
import com.google.inject.Module;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EuroMillionsPredictorModule implements Module {

    private static final Logger LOGGER = LoggerFactory.getLogger(EuroMillionsPredictorModule.class);

    private static final SubTypesScanner SUB_TYPES_SCANNER_INCLUDE_OBJECT_CLASS = new SubTypesScanner(false);

    private final DBI dbi;

    public EuroMillionsPredictorModule(Environment environment, EuroMillionsPredictorConfiguration euroMillionsPredictorConfiguration) {

        dbi = (new DBIFactory()).build(environment, euroMillionsPredictorConfiguration.getDataSourceFactory(), "odds-predictor");
    }

    @Override
    public void configure(Binder binder) {

        final String daoPackageName = getClass().getPackage().getName() + ".dao";

        (new Reflections(daoPackageName, SUB_TYPES_SCANNER_INCLUDE_OBJECT_CLASS)).getSubTypesOf(Dao.class).stream().forEach(daoClass -> {

            if (daoClass.getInterfaces().length > 0 && daoClass.getInterfaces()[0] == Dao.class) {

                try {

                    final Class<Dao> daoBindClass = (Class<Dao>) Class.forName(daoClass.getName().replace(".dao.", ".dao.jdbi.") + "Jdbi");

                    binder.bind((Class<Dao>) daoClass).toInstance(dbi.onDemand(daoBindClass));

                    LOGGER.info("The DAO Class [{}] has been bind to [{}].", daoClass.getName(), daoBindClass.getName());

                } catch (ClassNotFoundException e) {

                    LOGGER.error("Unable to load/bind the DAO Class [{}]: {}.", daoClass.getName(), e.getMessage());

                    throw new RuntimeException(e);
                }
            }
        });
    }
}
