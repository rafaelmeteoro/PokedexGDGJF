package br.com.rafael.pokedexgdgjf.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import br.com.rafael.pokedexgdgjf.BuildConfig;
import br.com.rafael.pokedexgdgjf.data.local.RealmHelper;
import br.com.rafael.pokedexgdgjf.injection.component.DaggerLibraryComponent;
import br.com.rafael.pokedexgdgjf.injection.component.LibraryComponent;
import br.com.rafael.pokedexgdgjf.injection.module.LibraryModule;
import br.com.rafael.pokedexgdgjf.injection.module.NetworkModule;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Created by rafael on 8/25/16.
 **/
public class PokedexApplication extends Application {

    private LibraryComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysys.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        initTimber();
        initRealm();
        initDagger();
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) Timber.plant(new Timber.DebugTree());
    }

    private void initRealm() {
        RealmConfiguration confg = new RealmConfiguration.Builder(this)
                .name(RealmHelper.POKEDEX_DB_NAME)
                .schemaVersion(RealmHelper.POKEDEX_DB_VERSION)
                .build();
        Realm.setDefaultConfiguration(confg);
    }

    private void initDagger() {
        component = DaggerLibraryComponent.builder()
                .libraryModule(new LibraryModule(getApplicationContext()))
                .networkModule(new NetworkModule(BuildConfig.API_URL))
                .build();
    }

    public LibraryComponent getComponent() {
        return component;
    }

    public static PokedexApplication get(Context context) {
        return (PokedexApplication) context.getApplicationContext();
    }
}
