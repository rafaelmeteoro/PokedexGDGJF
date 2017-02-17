package br.com.rafael.pokedexgdgjf.ui.favoritos;

import java.util.List;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.ui.base.MvpPresenter;
import br.com.rafael.pokedexgdgjf.ui.base.MvpView;

/**
 * Created by rafael on 8/29/16.
 **/
public interface FavoritosContract {

    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void showPokemons(List<Pokemon> pokemons);

        void showEmpty();

        void showErro();

        void showMessage();
    }

    interface Presenter extends MvpPresenter<View> {
        void getFavoritos();

        void deletePokemon(Pokemon pokemon);
    }
}
