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

    private final Retrofit mRetrofit;
    private final Context mContext;

    @Inject
    public ApiProvider(Retrofit retrofit, Context context) {
        mRetrofit = retrofit;
        mContext = context;
    }

    public PokedexService getPokedexService() {
        return new MockPokedexService(mContext);
    }
}
