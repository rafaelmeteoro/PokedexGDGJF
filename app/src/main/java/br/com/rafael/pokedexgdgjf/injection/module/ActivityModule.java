package br.com.rafael.pokedexgdgjf.injection.module;

import android.app.Activity;
import android.content.Context;

import br.com.rafael.pokedexgdgjf.injection.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 8/25/16.
 **/
@Module
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }
}
