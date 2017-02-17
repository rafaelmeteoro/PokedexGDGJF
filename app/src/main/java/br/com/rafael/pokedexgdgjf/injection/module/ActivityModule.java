package br.com.rafael.pokedexgdgjf.injection.module;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import br.com.rafael.pokedexgdgjf.injection.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 8/25/16.
 **/
@Module
public class ActivityModule {

    private final AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    FragmentManager fragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
