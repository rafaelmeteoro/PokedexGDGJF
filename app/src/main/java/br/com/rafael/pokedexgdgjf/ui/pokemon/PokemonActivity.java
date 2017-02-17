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
import br.com.rafael.pokedexgdgjf.ui.base.BaseActivity;
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
    protected PokemonContract.Presenter presenter;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.content_view)
    protected ScrollView contentView;

    @BindView(R.id.loading_view)
    protected ProgressBar loadingView;

    @BindView(R.id.error_view)
    protected TextView errorView;

    @BindView(R.id.iv_pokemon)
    protected CircleImageView pokemonImage;

    @BindView(R.id.pokemon_name)
    protected TextView pokemonName;

    @BindView(R.id.pokemon_weight)
    protected TextView pokemonWeight;

    @BindView(R.id.pokemon_height)
    protected TextView pokemonHeight;

    private int pokemonId;
    private Pokemon pokemon;

    PokemonComponent component;

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
        if (presenter != null) {
            presenter.detachView();
        }
    }

    private void initialize() {
        pokemonId = getIntent().getIntExtra(EXTRA_POKEMON_ID, DEFAULT_ID);
    }

    private void initializeToolBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializeInjection() {
        component = DaggerPokemonComponent.builder()
                .libraryComponent(((PokedexApplication) getApplication()).getComponent())
                .activityModule(getActivityModule())
                .pokemonModule(new PokemonModule())
                .build();
        component.inject(this);
    }

    private void initializePresenter() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    private void initializeContents() {
        presenter.getPokemon(pokemonId);
    }

    @Override
    public PokemonComponent getComponent() {
        return component;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pokemon, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_save).setVisible(pokemon != null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_save:
                presenter.savePokmon(pokemon);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.error_view)
    public void onReloadClick() {
        presenter.getPokemon(pokemonId);
    }

    @Override
    public void showProgress() {
        contentView.setVisibility(View.GONE);
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        contentView.setVisibility(View.GONE);
        errorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sentiment_very_dissatisfied_gray, 0, 0);
        errorView.setText(getString(R.string.activity_pokemon_load_error));
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        contentView.setVisibility(View.VISIBLE);
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
                contentView,
                R.string.activity_pokemon_saved,
                Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
