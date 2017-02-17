package br.com.rafael.pokedexgdgjf.ui.base;

import android.support.v7.app.AppCompatActivity;

import br.com.rafael.pokedexgdgjf.application.BaseApplication;
import br.com.rafael.pokedexgdgjf.injection.component.ApplicationComponent;
import br.com.rafael.pokedexgdgjf.injection.module.ActivityModule;

/**
 * Created by rafael on 2/14/17.
 **/

public class BaseActivity extends AppCompatActivity {

    public ApplicationComponent getApplicationComponent() {
        return ((BaseApplication) getApplication()).getApplicationComponent();
    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
