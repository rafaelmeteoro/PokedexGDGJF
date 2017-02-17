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
 * Created by rafael on 2/15/17.
 **/

@Module
public class LibraryModule {

    private final Context context;

    public LibraryModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return context;
    }

    @Provides
    @Singleton
    @Named(ApplicationModule.MAIN_THREAD)
    Scheduler provideMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(ApplicationModule.JOB_THREAD)
    Scheduler provideJobScheduler() {
        return Schedulers.computation();
    }
}
