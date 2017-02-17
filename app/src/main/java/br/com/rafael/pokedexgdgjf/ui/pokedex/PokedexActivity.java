package br.com.rafael.pokedexgdgjf.ui.pokedex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.application.PokedexApplication;
import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import br.com.rafael.pokedexgdgjf.injection.HasComponent;
import br.com.rafael.pokedexgdgjf.ui.base.BaseActivity;
import br.com.rafael.pokedexgdgjf.ui.di.component.DaggerPokedexComponent;
import br.com.rafael.pokedexgdgjf.ui.di.component.PokedexComponent;
import br.com.rafael.pokedexgdgjf.ui.di.module.PokedexModule;
import br.com.rafael.pokedexgdgjf.ui.favoritos.FavoritosActivity;
import br.com.rafael.pokedexgdgjf.ui.listener.OnPokemonClickListener;
import br.com.rafael.pokedexgdgjf.ui.pokemon.PokemonActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexActivity extends BaseActivity
        implements PokedexContract.View, HasComponent<PokedexComponent> {

    @Inject
    protected PokedexContract.Presenter presenter;

    @Inject
    protected PokedexAdapter adapter;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.content_view)
    protected SwipeRefreshLayout contentView;

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.loading_view)
    protected ProgressBar loadingView;

    @BindView(R.id.error_view)
    protected TextView errorView;

    PokedexComponent component;

    private final OnPokemonClickListener onPokemonClickListener =
            pokemonEntrie ->
                    startActivity(PokemonActivity.getStartIntent(this, pokemonEntrie.getEntryNumber()));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
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
        adapter.setListener(onPokemonClickListener);
        contentView.setEnabled(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void initializeToolBar() {
        setSupportActionBar(toolbar);
    }

    private void initializeInjection() {
        component = DaggerPokedexComponent.builder()
                .libraryComponent(((PokedexApplication) getApplication()).getComponent())
                .activityModule(getActivityModule())
                .pokedexModule(new PokedexModule())
                .build();
        component.inject(this);
    }

    private void initializePresenter() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    private void initializeContents() {
        presenter.getPokedex();
    }

    @Override
    public PokedexComponent getComponent() {
        return component;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_pokedex, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_favorito:
                startActivity(new Intent(this, FavoritosActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.error_view)
    public void onReloadClick() {
        presenter.getPokedex();
    }

    @Override
    public void showProgress() {
        if (recyclerView.getVisibility() == View.VISIBLE && adapter.getItemCount() > 0) {
            contentView.setRefreshing(true);
        } else {
            loadingView.setVisibility(View.VISIBLE);
        }
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        contentView.setRefreshing(false);
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showPokemonEntries(List<PokemonEntrie> list) {
        adapter.setList(list);
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {
        recyclerView.setVisibility(View.GONE);
        errorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_empty_glass_gray, 0, 0);
        errorView.setText(getString(R.string.activity_pokedex_load_list_empty));
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        recyclerView.setVisibility(View.GONE);
        errorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sentiment_very_dissatisfied_gray, 0, 0);
        errorView.setText(getString(R.string.activity_pokedex_load_list_error));
        errorView.setVisibility(View.VISIBLE);
    }
}
