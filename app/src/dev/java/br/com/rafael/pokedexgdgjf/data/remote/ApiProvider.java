package br.com.rafael.pokedexgdgjf.data.remote;

import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by rafael on 8/28/16.
 **/
public class ApiProvider {

    private final Retrofit retrofit;

    public ApiProvider(Retrofit retrofit, Context context) {
        this.retrofit = retrofit;
    }

    public PokedexService getPokedexService() {
        return retrofit.create(PokedexService.class);
    }
}
