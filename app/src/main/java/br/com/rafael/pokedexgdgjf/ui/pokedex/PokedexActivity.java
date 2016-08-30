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
import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import br.com.rafael.pokedexgdgjf.injection.component.ActivityComponent;
import br.com.rafael.pokedexgdgjf.ui.base.BaseMvpActivity;
import br.com.rafael.pokedexgdgjf.ui.favoritos.FavoritosActivity;
import br.com.rafael.pokedexgdgjf.ui.pokemon.PokemonActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexActivity extends BaseMvpActivity implements PokedexContract.View, PokedexAdapter.PokedexItemClickListener {

    @Inject
    protected PokedexPresenter mPresenter;

    @Inject
    protected PokedexAdapter mAdapter;

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.content_view)
    protected SwipeRefreshLayout mContentView;

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.loading_view)
    protected ProgressBar mLoadingView;

    @Bind(R.id.error_view)
    protected TextView mErrorView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);
        ButterKnife.bind(this);
        mPresenter.attachView(this);

        setSupportActionBar(mToolbar);
        setupViews();

        mPresenter.getPokedex();
    }

    private void setupViews() {
        mAdapter.setListener(this);
        mContentView.setEnabled(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
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
        mPresenter.getPokedex();
    }

    @Override
    public void onPokemonClick(PokemonEntrie pokemonEntrie) {
        startActivity(PokemonActivity.getStartIntent(this, pokemonEntrie.getEntryNumber()));
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
    public void showPokemonEntries(List<PokemonEntrie> list) {
        mAdapter.setList(list);
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
    public void showError() {
        mRecyclerView.setVisibility(View.GONE);
        mErrorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_sentiment_very_dissatisfied_gray, 0, 0);
        mErrorView.setText(getString(R.string.activity_pokedex_load_list_error));
        mErrorView.setVisibility(View.VISIBLE);
    }
}
