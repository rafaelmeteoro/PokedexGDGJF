package br.com.rafael.pokedexgdgjf.data.remote;

import android.content.Context;

import com.google.gson.Gson;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.helper.AssetsHelper;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rafael on 8/29/16.
 **/
public class MockPokedexService implements PokedexService {

    private Context mContext;

    public MockPokedexService(Context context) {
        mContext = context;
    }

    @Override
    public Observable<Pokedex> getPokedex(@Path("id") int id) {
        String pokedexJson;
        Pokedex pokedex;
        try {
            pokedexJson = AssetsHelper.getStringFromAssets(mContext, "pokedex_success.json");
            Gson gson = new Gson();
            pokedex = gson.fromJson(pokedexJson, Pokedex.class);
            return Observable.just(pokedex);
        } catch (Exception e) {
            return Observable.error(e);
        }
    }

    @Override
    public Observable<Pokemon> getPokemon(@Path("id") int id) {
        return null;
    }
}
