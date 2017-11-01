package com.andremanuelbarbosa.euromillions.predictor.dao.jdbi;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorIntegrationTest;
import com.andremanuelbarbosa.euromillions.predictor.dao.FormulasStatsDao;
import org.junit.BeforeClass;

public class FormulasStatsDaoJdbiIntegrationTest extends EuroMillionsPredictorIntegrationTest {

    private static FormulasStatsDaoJdbi formulasStatsDaoJdbi;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        initialize();

        formulasStatsDaoJdbi = (FormulasStatsDaoJdbi) injector.getInstance(FormulasStatsDao.class);
    }
}
