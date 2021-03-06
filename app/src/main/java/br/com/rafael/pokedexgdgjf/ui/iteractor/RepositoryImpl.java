package br.com.rafael.pokedexgdgjf.ui.iteractor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.injection.module.ApplicationModule;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by rafael on 2/16/17.
 **/

public class RepositoryImpl extends UseCase implements Repository {

    private final PokemonDao pokemonDao;

    @Inject
    public RepositoryImpl(@Named(ApplicationModule.JOB_THREAD) Scheduler jobScheduler,
                          @Named(ApplicationModule.MAIN_THREAD) Scheduler mainThreadScheduler,
                          PokemonDao pokemonDao) {
        super(jobScheduler, mainThreadScheduler);
        this.pokemonDao = pokemonDao;
    }

    @Override
    public Observable<List<Pokemon>> getPokemonsSaved() {
        return Observable.just(pokemonDao.getPokemonsSaved())
                .observeOn(getPostExecutationScheduler())
                .subscribeOn(getJobScheduler());
    }

    @Override
    public Observable<Boolean> saveUpdatePokemon(Pokemon pokemon) {
        return Observable.just(pokemonDao.saveUpdatePokemon(pokemon))
                .observeOn(getPostExecutationScheduler())
                .subscribeOn(getJobScheduler());
    }

    @Override
    public Observable<Boolean> deletePokemon(Pokemon pokemon) {
        return Observable.just(pokemonDao.deletePokemon(pokemon))
                .observeOn(getPostExecutationScheduler())
                .subscribeOn(getJobScheduler());
    }
}
