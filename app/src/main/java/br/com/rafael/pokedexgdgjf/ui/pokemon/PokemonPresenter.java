package br.com.rafael.pokedexgdgjf.ui.pokemon;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.R;
import br.com.rafael.pokedexgdgjf.data.DataManager;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.ui.base.BaseRxPresenter;
import rx.Subscriber;

/**
 * Created by rafael on 8/29/16.
 **/
public class PokemonPresenter extends BaseRxPresenter<PokemonContract.View> implements PokemonContract.Presenter {

    protected DataManager mDataManager;

    @Inject
    public PokemonPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void getPokemon(int pokemonId) {
        checkViewAttached();
        showProgress();

        unsubscribe();
        mSubscription = mDataManager.getPokemon(pokemonId)
                .subscribe(new Subscriber<Pokemon>() {
                    @Override
                    public void onCompleted() {
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgress();
                        showError();
                    }

                    @Override
                    public void onNext(Pokemon pokemon) {
                        showPokemon(pokemon);
                    }
                });
    }

    @Override
    public void savePokmon(Pokemon pokemon) {
        checkViewAttached();

        unsubscribe();
        mSubscription = mDataManager.saveUpdatePokemon(pokemon)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean value) {
                        if (value) {
                            getMvpView().showMessage(R.string.activity_pokemon_saved);
                        }
                    }
                });
    }

    private void showProgress() {
        getMvpView().showProgress();
    }

    private void hideProgress() {
        getMvpView().hideProgress();
    }

    private void showError() {
        getMvpView().showError();
    }

    private void showPokemon(Pokemon pokemon) {
        getMvpView().showPokemonName(pokemon.getName());
        getMvpView().showPokemonHeight(pokemon.getHeight());
        getMvpView().showPokemonWeight(pokemon.getWeight());
        getMvpView().showPokemonImage(pokemon.getSprites().getFrontDefault());
        getMvpView().showPokemon(pokemon);
    }
}
