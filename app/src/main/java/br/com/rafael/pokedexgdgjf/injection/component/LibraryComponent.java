package br.com.rafael.pokedexgdgjf.injection.component;

import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import br.com.rafael.pokedexgdgjf.injection.module.LibraryModule;
import br.com.rafael.pokedexgdgjf.injection.module.NetworkModule;
import dagger.Component;

/**
 * Created by rafael on 2/15/17.
 **/

@Singleton
@Component(modules = {LibraryModule.class, NetworkModule.class})
public interface LibraryComponent extends ApplicationComponent {

    ApiProvider apiProvider();
}
