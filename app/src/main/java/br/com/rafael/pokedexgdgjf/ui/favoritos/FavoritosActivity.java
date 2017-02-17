package br.com.rafael.pokedexgdgjf.ui.favoritos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.application.PokedexApplication;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.injection.HasComponent;
import br.com.rafael.pokedexgdgjf.ui.base.BaseActivity;
import br.com.rafael.pokedexgdgjf.ui.di.component.DaggerFavoritosComponent;
import br.com.rafael.pokedexgdgjf.ui.di.component.FavoritosComponent;
import br.com.rafael.pokedexgdgjf.ui.di.module.FavoritosModule;
import br.com.rafael.pokedexgdgjf.ui.listener.OnFavoritosClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 8/29/16.
 **/
public class FavoritosActivity extends BaseActivity
        implements FavoritosContract.View, HasComponent<FavoritosComponent> {

    private static final int NUM_COLUMN = 2;

    @Inject
    protected FavoritosContract.Presenter presenter;

    @Inject
    protected FavoritosAdapter adapter;

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

    FavoritosComponent component;

    private final OnFavoritosClickListener onFavoritosClickListener =
            pokemon -> presenter.deletePokemon(pokemon);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
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
        adapter.setListener(onFavoritosClickListener);
        contentView.setEnabled(false);

        recyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLUMN));
        recyclerView.setAdapter(adapter);
    }

    private void initializeToolBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializeInjection() {
        component = DaggerFavoritosComponent.builder()
                .libraryComponent(((PokedexApplication) getApplication()).getComponent())
                .activityModule(getActivityModule())
                .favoritosModule(new FavoritosModule())
                .build();
        component.inject(this);
    }

    private void initializePresenter() {
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    private void initializeContents() {
        presenter.getFavoritos();
    }

    @Override
    public FavoritosComponent getComponent() {
        return component;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.error_view)
    public void onReloadClick() {
        presenter.getFavoritos();
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
    public void showPokemons(List<Pokemon> pokemons) {
        adapter.setList(pokemons);
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
    public void showErro() {
        recyclerView.setVisibility(View.GONE);
        errorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sentiment_very_dissatisfied_gray, 0, 0);
        errorView.setText(getString(R.string.activity_pokedex_load_list_error));
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage() {
        Snackbar snackbar = Snackbar.make(
                contentView,
                R.string.activity_favorito_deleted,
                Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
