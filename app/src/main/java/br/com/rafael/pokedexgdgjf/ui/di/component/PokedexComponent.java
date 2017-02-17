package br.com.rafael.pokedexgdgjf.ui.di.component;

import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import br.com.rafael.pokedexgdgjf.injection.component.ActivityComponent;
import br.com.rafael.pokedexgdgjf.injection.component.LibraryComponent;
import br.com.rafael.pokedexgdgjf.injection.module.ActivityModule;
import br.com.rafael.pokedexgdgjf.ui.di.module.PokedexModule;
import br.com.rafael.pokedexgdgjf.ui.pokedex.PokedexActivity;
import dagger.Component;

/**
 * Created by rafael on 2/14/17.
 *
 */

@PerActivity
@Component(
        dependencies = {LibraryComponent.class},
        modules = {ActivityModule.class, PokedexModule.class}
)
public interface PokedexComponent extends ActivityComponent {
    void inject(PokedexActivity activity);
}
