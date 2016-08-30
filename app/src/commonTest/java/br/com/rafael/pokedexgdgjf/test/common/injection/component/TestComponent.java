package br.com.rafael.pokedexgdgjf.test.common.injection.component;

import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.injection.component.ApplicationComponent;
import br.com.rafael.pokedexgdgjf.injection.module.NetworkModule;
import br.com.rafael.pokedexgdgjf.test.common.injection.module.ApplicationTestModule;
import dagger.Component;

/**
 * Created by rafael on 8/29/16.
 **/
@Singleton
@Component(modules = {
        ApplicationTestModule.class,
        NetworkModule.class
})
public interface TestComponent extends ApplicationComponent {

}
