package br.com.rafael.pokedexgdgjf.ui.base;

import br.com.rafael.pokedexgdgjf.ui.exception.MvpViewNotAttachedException;

/**
 * Created by rafael on 2/14/17.
 *
 */

public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T mAttachedView;

    @Override
    public void attachView(T view) {
        mAttachedView = view;
    }

    @Override
    public void detachView() {
        clean();
        mAttachedView = null;
    }

    protected T getView() {
        if (mAttachedView == null) {
            throw new MvpViewNotAttachedException(getClass().getSimpleName());
        }
        return mAttachedView;
    }

    protected abstract void clean();
}
