package br.com.rafael.pokedexgdgjf.data.remote;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * Created by rafael on 8/29/16.
 **/
@Singleton
public class ApiProvider {

    private final Retrofit retrofit;
    private final Context context;

    @Inject
    public ApiProvider(Retrofit retrofit, Context context) {
        this.retrofit = retrofit;
        this.context = context;
    }

    public PokedexService getPokedexService() {
        return new MockPokedexService(context);
    }
}
