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
    protected FavoritosContract.Presenter mPresenter;

    @Inject
    protected FavoritosAdapter mAdapter;

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @BindView(R.id.content_view)
    protected SwipeRefreshLayout mContentView;

    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.loading_view)
    protected ProgressBar mLoadingView;

    @BindView(R.id.error_view)
    protected TextView mErrorView;

    FavoritosComponent mComponent;

    private final OnFavoritosClickListener mOnFavoritosClickListener =
            pokemon -> mPresenter.deletePokemon(pokemon);

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
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    private void initialize() {
        mAdapter.setListener(mOnFavoritosClickListener);
        mContentView.setEnabled(false);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLUMN));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initializeToolBar() {
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializeInjection() {
        mComponent = DaggerFavoritosComponent.builder()
                .libraryComponent(((PokedexApplication) getApplication()).getComponent())
                .activityModule(getActivityModule())
                .favoritosModule(new FavoritosModule())
                .build();
        mComponent.inject(this);
    }

    private void initializePresenter() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    private void initializeContents() {
        mPresenter.getFavoritos();
    }

    @Override
    public FavoritosComponent getComponent() {
        return mComponent;
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
        mPresenter.getFavoritos();
    }

    @Override
    public void showProgress() {
        if (mRecyclerView.getVisibility() == View.VISIBLE && mAdapter.getItemCount() > 0) {
            mContentView.setRefreshing(true);
        } else {
            mLoadingView.setVisibility(View.VISIBLE);
        }
        mErrorView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        mContentView.setRefreshing(false);
        mLoadingView.setVisibility(View.GONE);
    }

    @Override
    public void showPokemons(List<Pokemon> pokemons) {
        mAdapter.setList(pokemons);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmpty() {
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_empty_glass_gray, 0, 0);
        mErrorView.setText(getString(R.string.activity_pokedex_load_list_empty));
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErro() {
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sentiment_very_dissatisfied_gray, 0, 0);
        mErrorView.setText(getString(R.string.activity_pokedex_load_list_error));
        mErrorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage() {
        Snackbar snackbar = Snackbar.make(
                mContentView,
                R.string.activity_favorito_deleted,
                Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
