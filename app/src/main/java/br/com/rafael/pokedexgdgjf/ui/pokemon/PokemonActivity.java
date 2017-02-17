package br.com.rafael.pokedexgdgjf.ui.pokemon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.application.PokedexApplication;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.injection.HasComponent;
import br.com.rafael.pokedexgdgjf.injection.component.ActivityComponent;
import br.com.rafael.pokedexgdgjf.ui.base.BaseActivity;
import br.com.rafael.pokedexgdgjf.ui.base.BaseMvpActivity;
import br.com.rafael.pokedexgdgjf.ui.di.component.DaggerPokemonComponent;
import br.com.rafael.pokedexgdgjf.ui.di.component.PokemonComponent;
import br.com.rafael.pokedexgdgjf.ui.di.module.PokemonModule;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rafael on 8/29/16.
 **/
public class PokemonActivity extends BaseActivity
        implements PokemonContract.View, HasComponent<PokemonComponent> {

    public static final String EXTRA_POKEMON_ID = "EXTRA_POKEMON_ID";
    private static final int DEFAULT_ID = 0;

    @Inject
    protected PokemonContract.Presenter mPresenter;

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.content_view)
    protected ScrollView mContentView;

    @BindView(R.id.loading_view)
    protected ProgressBar mLoadingView;

    @BindView(R.id.error_view)
    protected TextView mErrorView;

    @BindView(R.id.iv_pokemon)
    protected CircleImageView pokemonImage;

    @BindView(R.id.pokemon_name)
    protected TextView pokemonName;

    @BindView(R.id.pokemon_weight)
    protected TextView pokemonWeight;

    @BindView(R.id.pokemon_height)
    protected TextView pokemonHeight;

    private int mPokemonId;
    private Pokemon mPokemon;

    PokemonComponent mComponent;

    public static Intent getStartIntent(Context context, int pokemonId) {
        Intent intent = new Intent(context, PokemonActivity.class);
        intent.putExtra(EXTRA_POKEMON_ID, pokemonId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        ButterKnife.bind(this);

        initializeToolBar();
        initializeInjection();
        initialize();
        initializePresenter();
        initializeContents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializePresenter();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    private void initialize() {
        mPokemonId = getIntent().getIntExtra(EXTRA_POKEMON_ID, DEFAULT_ID);
    }

    private void initializeToolBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializeInjection() {
        mComponent = DaggerPokemonComponent.builder()
                .libraryComponent(((PokedexApplication) getApplication()).getComponent())
                .activityModule(getActivityModule())
                .pokemonModule(new PokemonModule())
                .build();
        mComponent.inject(this);
    }

    private void initializePresenter() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private void initializeContents() {
        mPresenter.getPokemon(mPokemonId);
    }

    @Override
    public PokemonComponent getComponent() {
        return mComponent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pokemon, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_save).setVisible(mPokemon != null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_save:
                mPresenter.savePokmon(mPokemon);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.error_view)
    public void onReloadClick() {
        mPresenter.getPokemon(mPokemonId);
    }

    @Override
    public void showProgress() {
        mContentView.setVisibility(View.GONE);
        mLoadingView.setVisibility(View.VISIBLE);
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        mContentView.setVisibility(View.GONE);
        mErrorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sentiment_very_dissatisfied_gray, 0, 0);
        mErrorView.setText(getString(R.string.activity_pokemon_load_error));
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPokemon(Pokemon pokemon) {
        mPokemon = pokemon;
        mContentView.setVisibility(View.VISIBLE);
        invalidateOptionsMenu();
    }

    @Override
    public void showPokemonImage(String url) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.pokeball)
                .error(R.drawable.cloud_outline_off)
                .into(pokemonImage);
    }

    @Override
    public void showPokemonName(String name) {
        pokemonName.setText(name);
    }

    @Override
    public void showPokemonWeight(int weight) {
        pokemonWeight.setText(String.valueOf(weight));
    }

    @Override
    public void showPokemonHeight(int height) {
        pokemonHeight.setText(String.valueOf(height));
    }

    @Override
    public void showMessage() {
        Snackbar snackbar = Snackbar.make(
                mContentView,
                R.string.activity_pokemon_saved,
                Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
