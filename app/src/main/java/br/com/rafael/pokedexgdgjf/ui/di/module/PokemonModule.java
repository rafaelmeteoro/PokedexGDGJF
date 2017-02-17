package br.com.rafael.pokedexgdgjf.ui.di.module;

import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import br.com.rafael.pokedexgdgjf.ui.iteractor.GetPokemon;
import br.com.rafael.pokedexgdgjf.ui.iteractor.GetPokemonImpl;
import br.com.rafael.pokedexgdgjf.ui.iteractor.Repository;
import br.com.rafael.pokedexgdgjf.ui.iteractor.RepositoryImpl;
import br.com.rafael.pokedexgdgjf.ui.pokemon.PokemonContract;
import br.com.rafael.pokedexgdgjf.ui.pokemon.PokemonPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 2/16/17.
 **/

@Module
public class PokemonModule {

    @Provides
    @PerActivity
    GetPokemon provideGetPokemon(GetPokemonImpl useCase) {
        return useCase;
    }

    @Provides
    @PerActivity
    Repository provideRepository(RepositoryImpl useCase) {
        return useCase;
    }

    @Provides
    @PerActivity
    PokemonContract.Presenter providePresenter(PokemonPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PokemonDao providePokemonDao() {
        return new PokemonDao();
    }
}
