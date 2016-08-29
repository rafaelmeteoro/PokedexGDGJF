package br.com.rafael.pokedexgdgjf.injection.component;

import android.content.Context;

import br.com.rafael.pokedexgdgjf.injection.ActivityContext;
import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import br.com.rafael.pokedexgdgjf.injection.module.ActivityModule;
import br.com.rafael.pokedexgdgjf.ui.pokedex.PokedexActivity;
import br.com.rafael.pokedexgdgjf.ui.pokemon.PokemonActivity;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by rafael on 8/25/16.
 **/
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(PokedexActivity pokedexActivity);

    void inject(PokemonActivity pokemonActivity);

    Retrofit retrofit();

    @ActivityContext
    Context context();
}
