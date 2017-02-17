package br.com.rafael.pokedexgdgjf.injection.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rafael on 8/25/16.
 **/
@Module
public class ApplicationModule {

    public static final String MAIN_THREAD = "mainThreadScheduler";
    public static final String JOB_THREAD = "jobScheduler";

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    @Named(MAIN_THREAD)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(JOB_THREAD)
    Scheduler provideJobScheduler() {
        return Schedulers.io();
    }
}
