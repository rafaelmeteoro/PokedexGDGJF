package br.com.rafael.pokedexgdgjf.data.remote;

import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by rafael on 8/28/16.
 **/
public class ApiProvider {

    private final Retrofit mRetrofit;

    public ApiProvider(Retrofit retrofit, Context context) {
        mRetrofit = retrofit;
    }

    public PokedexService getPokedexService() {
        return mRetrofit.create(PokedexService.class);
    }
}
