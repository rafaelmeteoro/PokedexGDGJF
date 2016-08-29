package br.com.rafael.pokedexgdgjf.ui.pokedex;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.injection.component.ActivityComponent;
import br.com.rafael.pokedexgdgjf.ui.base.BaseMvpActivity;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexActivity extends BaseMvpActivity implements PokedexContract.View {

    @Inject
    PokedexPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        mPresenter.attachView(this);

        mPresenter.getPokedex();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }
}
