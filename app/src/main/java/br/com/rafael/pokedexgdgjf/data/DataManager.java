package br.com.rafael.pokedexgdgjf.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.data.operator.WorkerOperatorProvider;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import rx.Observable;

/**
 * Created by rafael on 8/25/16.
 **/
@Singleton
public class DataManager {

    private final ApiProvider mApiProvider;
    private final PokemonDao mPokemonDao;
    private final WorkerOperatorProvider mWorkerProvider;

    @Inject
    public DataManager(ApiProvider apiProvider, PokemonDao pokemonDao, WorkerOperatorProvider workerOperatorProvider) {
        mApiProvider = apiProvider;
        mPokemonDao = pokemonDao;
        mWorkerProvider = workerOperatorProvider;
    }

    public Observable<Pokedex> getPodedex() {
        // Neste exemplo pokedex id é fixo
        int pokedexId = 2;
        return mApiProvider
                .getPokedexService()
                .getPokedex(pokedexId)
                .compose(mWorkerProvider.getWorkerOperatorPokedex());
    }

    public Observable<Pokemon> getPokemon(int pokemonId) {
        return mApiProvider
                .getPokedexService()
                .getPokemon(pokemonId)
                .compose(mWorkerProvider.getWorkerOperatorPokemon());
    }

    public Observable<List<Pokemon>> getPokemonsSaved() {
        return Observable.just(mPokemonDao.getPokemonsSaved())
                .compose(mWorkerProvider.getWorkerOperatorListPokemon());
    }

    public Observable<Boolean> saveUpdatePokemon(Pokemon pokemon) {
        return Observable.just(mPokemonDao.saveUpdatePokemon(pokemon))
                .compose(mWorkerProvider.getWorkerOperatorBoolean());
    }

    public Observable<Boolean> deletePokemon(Pokemon pokemon) {
        return Observable.just(mPokemonDao.deletePokemon(pokemon))
                .compose(mWorkerProvider.getWorkerOperatorBoolean());
    }
}
