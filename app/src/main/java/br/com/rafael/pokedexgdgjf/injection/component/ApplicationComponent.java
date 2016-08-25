package br.com.rafael.pokedexgdgjf.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.PokedexApplication;
import br.com.rafael.pokedexgdgjf.data.DataManager;
import br.com.rafael.pokedexgdgjf.injection.ApplicationContext;
import br.com.rafael.pokedexgdgjf.injection.module.ApplicationModule;
import dagger.Component;

/**
 * Created by rafael on 8/25/16.
 **/
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(PokedexApplication application);

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();
}
