package br.com.rafael.pokedexgdgjf.ui.base;

import br.com.rafael.pokedexgdgjf.ui.exception.MvpViewNotAttachedException;

/**
 * Created by rafael on 2/14/17.
 *
 */

public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T attachedView;

    @Override
    public void attachView(T view) {
        attachedView = view;
    }

    @Override
    public void detachView() {
        clean();
        attachedView = null;
    }

    protected T getView() {
        if (attachedView == null) {
            throw new MvpViewNotAttachedException(getClass().getSimpleName());
        }
        return attachedView;
    }

    protected abstract void clean();
}
