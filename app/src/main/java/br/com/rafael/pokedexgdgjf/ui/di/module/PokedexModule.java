package br.com.rafael.pokedexgdgjf.ui.di.module;

import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import br.com.rafael.pokedexgdgjf.ui.iteractor.GetPokedex;
import br.com.rafael.pokedexgdgjf.ui.iteractor.GetPokedexImpl;
import br.com.rafael.pokedexgdgjf.ui.pokedex.PokedexContract;
import br.com.rafael.pokedexgdgjf.ui.pokedex.PokedexPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 2/14/17.
 *
 */

@Module
public class PokedexModule {

    @Provides
    @PerActivity
    GetPokedex provideGetPokedex(GetPokedexImpl useCase) {
        return useCase;
    }

    @Provides
    @PerActivity
    PokedexContract.Presenter providePresenter(PokedexPresenter presenter) {
        return presenter;
    }
}
