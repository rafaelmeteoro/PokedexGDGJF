package br.com.rafael.pokedexgdgjf;

import android.app.Application;
import android.content.Context;

import br.com.rafael.pokedexgdgjf.injection.component.ApplicationComponent;
import br.com.rafael.pokedexgdgjf.injection.component.DaggerApplicationComponent;
import br.com.rafael.pokedexgdgjf.injection.module.ApplicationModule;
import timber.log.Timber;

/**
 * Created by rafael on 8/25/16.
 **/
public class PokedexApplication extends Application {

    protected ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static PokedexApplication get(Context context) {
        return (PokedexApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
