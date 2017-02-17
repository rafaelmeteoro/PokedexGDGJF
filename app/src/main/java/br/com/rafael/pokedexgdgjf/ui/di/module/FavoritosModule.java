package br.com.rafael.pokedexgdgjf.ui.di.module;

import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import br.com.rafael.pokedexgdgjf.ui.favoritos.FavoritosContract;
import br.com.rafael.pokedexgdgjf.ui.favoritos.FavoritosPresenter;
import br.com.rafael.pokedexgdgjf.ui.iteractor.Repository;
import br.com.rafael.pokedexgdgjf.ui.iteractor.RepositoryImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 2/16/17.
 **/

@Module
public class FavoritosModule {

    @Provides
    @PerActivity
    Repository provideRepository(RepositoryImpl useCase) {
        return useCase;
    }

    @Provides
    @PerActivity
    FavoritosContract.Presenter providePresenter(FavoritosPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    PokemonDao providePokemonDao() {
        return new PokemonDao();
    }
}
