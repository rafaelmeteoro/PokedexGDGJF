package br.com.rafael.pokedexgdgjf.injection.component;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.injection.module.ApplicationModule;
import br.com.rafael.pokedexgdgjf.ui.base.BaseActivity;
import dagger.Component;
import rx.Scheduler;

/**
 * Created by rafael on 8/25/16.
 **/
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity activity);

    Context context();

    @Named(ApplicationModule.MAIN_THREAD)
    Scheduler mainThreadScheduler();

    @Named(ApplicationModule.JOB_THREAD)
    Scheduler jobScheduler();
}
