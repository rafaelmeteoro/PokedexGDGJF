package br.com.rafael.pokedexgdgjf.data;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import rx.Observable;

/**
 * Created by rafael on 8/25/16.
 **/
@Singleton
public class DataManager {

    private final ApiProvider mApiProvider;

    @Inject
    public DataManager(ApiProvider apiProvider) {
        mApiProvider = apiProvider;
    }

    public Observable<Pokedex> getPodedex() {
        // Neste exemplo pokedex id é fixo
        int pokedexId = 2;
        return mApiProvider
                .getPokedexService()
                .getPokedex(pokedexId);
    }

    public Observable<Pokemon> getPokemon(int pokemonId) {
        return mApiProvider
                .getPokedexService()
                .getPokemon(pokemonId);
    }
}
