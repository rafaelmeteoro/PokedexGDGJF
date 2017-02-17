package br.com.rafael.pokedexgdgjf.ui.di.component;

import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import br.com.rafael.pokedexgdgjf.injection.component.LibraryComponent;
import br.com.rafael.pokedexgdgjf.injection.module.ActivityModule;
import br.com.rafael.pokedexgdgjf.ui.di.module.FavoritosModule;
import br.com.rafael.pokedexgdgjf.ui.favoritos.FavoritosActivity;
import dagger.Component;

/**
 * Created by rafael on 2/16/17.
 **/

@PerActivity
@Component(
        dependencies = {LibraryComponent.class},
        modules = {ActivityModule.class, FavoritosModule.class}
)
public interface FavoritosComponent {
    void inject(FavoritosActivity activity);
}
