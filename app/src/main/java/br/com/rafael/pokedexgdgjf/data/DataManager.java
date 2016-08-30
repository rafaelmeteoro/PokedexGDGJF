package br.com.rafael.pokedexgdgjf.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.data.operator.WorkerOperator;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import rx.Observable;

/**
 * Created by rafael on 8/25/16.
 **/
@Singleton
public class DataManager {

    private final ApiProvider mApiProvider;
    private final PokemonDao mPokemonDao;

    @Inject
    public DataManager(ApiProvider apiProvider, PokemonDao pokemonDao) {
        mApiProvider = apiProvider;
        mPokemonDao = pokemonDao;
    }

    public Observable<Pokedex> getPokedex() {
        // Neste exemplo pokedex id é fixo
        int pokedexId = 2;
        return mApiProvider
                .getPokedexService()
                .getPokedex(pokedexId)
                .compose(new WorkerOperator<Pokedex>());
    }

    public Observable<Pokemon> getPokemon(int pokemonId) {
        return mApiProvider
                .getPokedexService()
                .getPokemon(pokemonId)
                .compose(new WorkerOperator<Pokemon>());
    }

    public Observable<List<Pokemon>> getPokemonsSaved() {
        return Observable.just(mPokemonDao.getPokemonsSaved())
                .compose(new WorkerOperator<List<Pokemon>>());
    }

    public Observable<Boolean> saveUpdatePokemon(Pokemon pokemon) {
        return Observable.just(mPokemonDao.saveUpdatePokemon(pokemon))
                .compose(new WorkerOperator<Boolean>());
    }

    public Observable<Boolean> deletePokemon(Pokemon pokemon) {
        return Observable.just(mPokemonDao.deletePokemon(pokemon))
                .compose(new WorkerOperator<Boolean>());
    }
}
