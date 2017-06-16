package com.andremanuelbarbosa.euromillions.predictor.jobs;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.manager.*;
import com.google.inject.Inject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// every wed and sat @ 00:00
@Cron("0 * * * * ?")
@DisallowConcurrentExecution
public class DrawsJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawsJob.class);

    private final DrawsManager drawsManager;
    private final DrawsStatsManager drawsStatsManager;
    private final DrawsTemplatesManager drawsTemplatesManager;
    private final DrawsFormulasManager drawsFormulasManager;

    private final FormulasManager formulasManager;
    private final FormulasStatsManager formulasStatsManager;

    @Inject
    public DrawsJob(DrawsManager drawsManager, DrawsStatsManager drawsStatsManager, DrawsTemplatesManager drawsTemplatesManager, DrawsFormulasManager drawsFormulasManager, FormulasManager formulasManager, FormulasStatsManager formulasStatsManager) {

        this.drawsManager = drawsManager;
        this.drawsStatsManager = drawsStatsManager;
        this.drawsTemplatesManager = drawsTemplatesManager;
        this.drawsFormulasManager = drawsFormulasManager;

        this.formulasManager = formulasManager;
        this.formulasStatsManager = formulasStatsManager;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOGGER.info("The Draws Job is running...");

        final List<Draw> drawsWithoutStarsOrNumbers = drawsManager.getDrawsWithoutStarsOrNumbers();

        LOGGER.info("Draws without Stars or Numbers: {}.", drawsWithoutStarsOrNumbers.size());

        if (drawsWithoutStarsOrNumbers.size() > 0) {

            drawsWithoutStarsOrNumbers.forEach(drawWithoutStarsOrNumbers -> {

                final List<Draw> drawsUpToIncludingDrawWithoutStarsOrNumbers = drawsManager.getDrawsUpToIncluding(drawWithoutStarsOrNumbers.getId());

                drawsManager.updateDrawUsingWebDriver(drawWithoutStarsOrNumbers);

                drawsStatsManager.updateDrawStats(drawWithoutStarsOrNumbers.getId(), drawsUpToIncludingDrawWithoutStarsOrNumbers);
                drawsTemplatesManager.updateDrawsTemplates(drawsUpToIncludingDrawWithoutStarsOrNumbers, drawWithoutStarsOrNumbers.getId(), false);

                drawsFormulasManager.updateDrawFormulas(drawWithoutStarsOrNumbers.getId(),
                    drawsManager.getDrawsUpToIncluding(drawWithoutStarsOrNumbers.getId(), true, true, true), formulasManager.getFormulas());

//                formulasStatsManager.updateFormulasStats(drawWithoutStarsOrNumbers.getId(),
//                    drawsManager.getDrawsUpToIncluding(drawWithoutStarsOrNumbers.getId(), true, true, true), formulasManager.getFormulas());
            });
        }

        LOGGER.info("The Draws Job has finished.");
    }
}
