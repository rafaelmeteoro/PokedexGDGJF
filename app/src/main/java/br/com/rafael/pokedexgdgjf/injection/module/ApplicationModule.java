package br.com.rafael.pokedexgdgjf.injection.module;

import android.app.Application;
import android.content.Context;

import br.com.rafael.pokedexgdgjf.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 8/25/16.
 **/
@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }
}
