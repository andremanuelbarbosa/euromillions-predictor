package com.andremanuelbarbosa.euromillions.predictor.jobs;

import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsStatsManager;
import com.google.inject.Inject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorProperties.AVAILABLE_PROCESSORS;

@Cron("0 0/5 * * * ?")
@DisallowConcurrentExecution
public class DrawsStatsJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawsStatsJob.class);

    private final DrawsStatsManager drawsStatsManager;

    private ExecutorService executorService;

    @Inject
    public DrawsStatsJob(DrawsStatsManager drawsStatsManager) {

        this.drawsStatsManager = drawsStatsManager;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOGGER.info("The Draws Stats Job is running...");

        final List<Integer> drawIdsWithoutStats = drawsStatsManager.getDrawIdsWithoutStats();

        LOGGER.info("Draws without Stats: {}.", drawIdsWithoutStats.size());

        executorService = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

        drawIdsWithoutStats.forEach(drawId -> {

            executorService.execute(new DrawStatsThread(drawId));
        });

        executorService.shutdown();

        try {

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            new IllegalStateException(e);
        }

        LOGGER.info("The Draws Stats Job has finished.");
    }

    class DrawStatsThread implements Runnable {

        private final int drawId;

        DrawStatsThread(int drawId) {

            this.drawId = drawId;
        }

        @Override
        public void run() {

            LOGGER.debug("Updating the Stats for Draw with ID [{}]...", drawId);

            drawsStatsManager.updateDrawStats(drawId);

            LOGGER.debug("The Stats for Draw with ID [{}] have been updated.", drawId);
        }
    }
}
