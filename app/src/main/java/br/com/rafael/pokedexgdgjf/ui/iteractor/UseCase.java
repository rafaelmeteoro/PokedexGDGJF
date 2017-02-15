package br.com.rafael.pokedexgdgjf.ui.iteractor;

import rx.Scheduler;

/**
 * Created by rafael on 2/14/17.
 **/

public class UseCase {

    private final Scheduler mJobScheduler;
    private final Scheduler mPostExecutationScheduler;

    public UseCase(Scheduler jobScheduler, Scheduler postExecutationScheduler) {
        mJobScheduler = jobScheduler;
        mPostExecutationScheduler = postExecutationScheduler;
    }

    protected Scheduler getJobScheduler() {
        return mJobScheduler;
    }

    protected Scheduler getPostExecutationScheduler() {
        return mPostExecutationScheduler;
    }
}
