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
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.injection.component.ActivityComponent;
import br.com.rafael.pokedexgdgjf.ui.base.BaseMvpActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 8/29/16.
 **/
public class FavoritosActivity extends BaseMvpActivity implements FavoritosContract.View, FavoritosAdapter.FavoritosItemClickListener {

    private static final int NUM_COLUMN = 2;

    @Inject
    protected FavoritosPresenter mPresenter;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        ButterKnife.bind(this);
        mPresenter.attachView(this);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setupView();

        mPresenter.getFavoritos();
    }

    private void setupView() {
        mAdapter.setListener(this);
        mContentView.setEnabled(false);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, NUM_COLUMN));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        //activityComponent.inject(this);
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
    public void onPokemonClick(Pokemon pokemon) {
        mPresenter.deletePokemon(pokemon);
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
    public void showMessage(int resId) {
        Snackbar snackbar = Snackbar.make(mContentView, resId, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
