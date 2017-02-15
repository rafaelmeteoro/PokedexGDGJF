package br.com.rafael.pokedexgdgjf.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.rafael.pokedexgdgjf.injection.component.ActivityComponent;

/**
 * Created by rafael on 8/25/16.
 **/
public abstract class BaseMvpActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
    }

    private void injectDependencies() {
        /*if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(PokedexApplication.get(this).getComponent())
                    .build();
        }
        inject(mActivityComponent);*/
    }

    protected abstract void inject(ActivityComponent activityComponent);
}
