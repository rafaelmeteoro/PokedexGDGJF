package br.com.rafael.pokedexgdgjf.ui.base;

/**
 * Created by rafael on 8/25/16.
 **/
public interface MvpPresenter<V extends MvpView> {

    void attachView(V view);

    void detachView();
}
