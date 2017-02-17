package br.com.rafael.pokedexgdgjf.ui.iteractor;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import br.com.rafael.pokedexgdgjf.injection.module.ApplicationModule;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by rafael on 2/16/17.
 **/

public class GetPokemonImpl extends UseCase implements GetPokemon {

    private final ApiProvider apiProvider;

    @Inject
    public GetPokemonImpl(@Named(ApplicationModule.JOB_THREAD) Scheduler jobScheduler,
                          @Named(ApplicationModule.MAIN_THREAD) Scheduler mainThreadScheduler,
                          ApiProvider apiProvider) {
        super(jobScheduler, mainThreadScheduler);
        this.apiProvider = apiProvider;
    }

    @Override
    public Observable<Pokemon> execute(int pokemonId) {
        return apiProvider
                .getPokedexService()
                .getPokemon(pokemonId)
                .observeOn(getPostExecutationScheduler())
                .subscribeOn(getJobScheduler());
    }
}
