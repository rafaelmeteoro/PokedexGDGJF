package br.com.rafael.pokedexgdgjf.data.remote;

import android.app.Application;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by rafael on 8/29/16.
 **/
@Singleton
public class ApiProvider {

    private final Retrofit mRetrofit;
    private final Application mApplication;

    @Inject
    public ApiProvider(Retrofit retrofit, Application application) {
        mRetrofit = retrofit;
        mApplication = application;
    }

    public PokedexService getPokedexService() {
        return new MockPokedexService(mApplication.getApplicationContext());
    }
}
