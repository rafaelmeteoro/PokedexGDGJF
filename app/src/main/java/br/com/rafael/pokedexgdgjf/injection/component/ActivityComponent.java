package br.com.rafael.pokedexgdgjf.injection.component;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import br.com.rafael.pokedexgdgjf.injection.module.ActivityModule;
import dagger.Component;

/**
 * Created by rafael on 8/25/16.
 **/
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    AppCompatActivity activity();

    FragmentManager fragmentManager();
}
