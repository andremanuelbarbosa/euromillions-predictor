package com.andremanuelbarbosa.euromillions.predictor.jobs;

import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsManager;
import com.andremanuelbarbosa.euromillions.predictor.manager.DrawsTemplatesManager;
import com.google.common.collect.Lists;
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

//@Cron("0 0/5 * * * ?")
@DisallowConcurrentExecution
public class DrawsTemplatesJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(DrawsTemplatesJob.class);

    private final DrawsManager drawsManager;
    private final DrawsTemplatesManager drawsTemplatesManager;

    private ExecutorService executorService;

    @Inject
    public DrawsTemplatesJob(DrawsManager drawsManager, DrawsTemplatesManager drawsTemplatesManager) {

        this.drawsManager = drawsManager;
        this.drawsTemplatesManager = drawsTemplatesManager;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        LOGGER.info("The Draws Templates Job is running...");

        final List<Integer> drawIdsWithoutTemplates = drawsTemplatesManager.getDrawIdsWithoutTemplates();

        LOGGER.info("Draws without Templates: {}.", drawIdsWithoutTemplates.size());

        executorService = Executors.newFixedThreadPool(AVAILABLE_PROCESSORS);

        drawIdsWithoutTemplates.forEach(drawId -> {

            executorService.execute(new DrawTemplatesThread(drawId));
        });

        executorService.shutdown();

        try {

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {

            new IllegalStateException(e);
        }

        LOGGER.info("The Draws Templates Job has finished.");
    }

    class DrawTemplatesThread implements Runnable {

        private final int drawId;

        DrawTemplatesThread(int drawId) {

            this.drawId = drawId;
        }

        @Override
        public void run() {

            LOGGER.debug("Updating the Templates for Draw with ID [{}]...", drawId);

            drawsTemplatesManager.updateDrawsTemplates(Lists.reverse(drawsManager.getDraws()).subList(0, drawId), drawId, false);

            LOGGER.debug("The Templates for Draw with ID [{}] have been updated.", drawId);
        }
    }
}
