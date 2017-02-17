package br.com.rafael.pokedexgdgjf.ui.iteractor;

import rx.Scheduler;

/**
 * Created by rafael on 2/14/17.
 **/

public class UseCase {

    private final Scheduler jobScheduler;
    private final Scheduler postExecutationScheduler;

    public UseCase(Scheduler jobScheduler, Scheduler postExecutationScheduler) {
        this.jobScheduler = jobScheduler;
        this.postExecutationScheduler = postExecutationScheduler;
    }

    protected Scheduler getJobScheduler() {
        return jobScheduler;
    }

    protected Scheduler getPostExecutationScheduler() {
        return postExecutationScheduler;
    }
}
