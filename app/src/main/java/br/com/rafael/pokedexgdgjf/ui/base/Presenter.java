package br.com.rafael.pokedexgdgjf.ui.base;

/**
 * Created by rafael on 8/25/16.
 **/
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
