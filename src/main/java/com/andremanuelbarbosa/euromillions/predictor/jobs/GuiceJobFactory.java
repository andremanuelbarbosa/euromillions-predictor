package com.andremanuelbarbosa.euromillions.predictor.jobs;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

public class GuiceJobFactory implements JobFactory {

    private final Injector injector;

    @Inject
    public GuiceJobFactory(Injector injector) {

        this.injector = injector;
    }

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {

        return (Job) injector.getInstance(triggerFiredBundle.getJobDetail().getJobClass());
    }
}
