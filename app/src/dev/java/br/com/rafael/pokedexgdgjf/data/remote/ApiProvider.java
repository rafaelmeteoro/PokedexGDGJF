package br.com.rafael.pokedexgdgjf.data.remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by rafael on 8/28/16.
 **/
@Singleton
public class ApiProvider {

    private final Retrofit mRetrofit;

    @Inject
    public ApiProvider(Retrofit retrofit) {
        mRetrofit = retrofit;
    }

    public PokedexService getPokedexService() {
        return mRetrofit.create(PokedexService.class);
    }
}
