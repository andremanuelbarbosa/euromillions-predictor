package com.andremanuelbarbosa.euromillions.predictor.jobs;

import com.andremanuelbarbosa.euromillions.predictor.domain.Draw;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsManager;
import com.andremanuelbarbosa.euromillions.predictor.manager.FormulasStatsManager;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//@Cron("0 * * * * ?")
@DisallowConcurrentExecution
public class FormulasStatsJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormulasStatsJob.class);

    private final DrawsManager drawsManager;
    private final FormulasStatsManager formulasStatsManager;

    @Inject
    public FormulasStatsJob(DrawsManager drawsManager, FormulasStatsManager formulasStatsManager) {

        this.drawsManager = drawsManager;
        this.formulasStatsManager = formulasStatsManager;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOGGER.info("The Formulas Stats Job is running...");

        final List<Integer> drawIdsWithoutFormulasStats = formulasStatsManager.getDrawIdsWithoutFormulasStats();

        LOGGER.info("Draws without Formulas Stats: {}.", drawIdsWithoutFormulasStats.size());

//        drawIdsWithoutFormulasStats.forEach(drawId -> {
//
//            final List<Draw> draws = Lists.reverse(drawsManager.getDraws(true, true, true)).subList(0, drawId);
//
//            formulasStatsManager.updateFormulasStats(drawId, 5, draws, drawId - 5, drawId - 1);
//            formulasStatsManager.updateFormulasStats(drawId, 10, draws, drawId - 10, drawId - 1);
//            formulasStatsManager.updateFormulasStats(drawId, 25, draws, drawId - 25, drawId - 1);
//            formulasStatsManager.updateFormulasStats(drawId, 50, draws, drawId - 50, drawId - 1);
//            formulasStatsManager.updateFormulasStats(drawId, 100, draws, drawId - 100, drawId - 1);
//        });

        LOGGER.info("The Formulas Stats Job has finished.");
    }
}
