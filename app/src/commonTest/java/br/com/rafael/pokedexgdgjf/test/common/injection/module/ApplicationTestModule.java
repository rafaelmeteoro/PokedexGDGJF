package br.com.rafael.pokedexgdgjf.test.common.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import br.com.rafael.pokedexgdgjf.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

/**
 * Created by rafael on 8/29/16.
 **/
@Module
public class ApplicationTestModule {

    protected final Application mApplication;

    public ApplicationTestModule(Application application) {
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

    /****** MOCKS ******/

    @Provides
    @Singleton
    ApiProvider provideApiProvider() {
        return mock(ApiProvider.class);
    }
}
