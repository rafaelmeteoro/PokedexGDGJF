package br.com.rafael.pokedexgdgjf.ui.pokedex;

import java.util.List;

import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import br.com.rafael.pokedexgdgjf.ui.base.MvpView;

/**
 * Created by rafael on 8/28/16.
 **/
public interface PokedexContract {

    interface View extends MvpView {
        void showProgress();

        void hideProgress();

        void showPokemonEntries(List<PokemonEntrie> list);

        void showEmpty();

        void showError();
    }

    interface Presenter {
        void getPokedex();
    }
}
