package br.com.rafael.pokedexgdgjf.ui.pokemon;

import br.com.rafael.pokedexgdgjf.ui.base.MvpView;

/**
 * Created by rafael on 8/29/16.
 **/
public interface PokemonContract {

    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void showError();

        void showPokemon();

        void showPokemonImage(String url);

        void showPokemonName(String name);

        void showPokemonWeight(int weight);

        void showPokemonHeight(int height);
    }

    interface Presenter {
        void getPokemon(int pokemonId);
    }
}
