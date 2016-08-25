package br.com.rafael.pokedexgdgjf.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 8/25/16.
 **/
@Module
public class ApplicationModule {

    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }
}
