package br.com.rafael.pokedexgdgjf.ui.pokedex;

import br.com.rafael.pokedexgdgjf.ui.base.MvpView;

/**
 * Created by rafael on 8/28/16.
 **/
public interface PokedexContract {

    interface View extends MvpView {

    }

    interface Presenter {
        void getPokedex();
    }
}
