package br.com.rafael.pokedexgdgjf.ui.base;

import rx.Subscription;

/**
 * Created by rafael on 8/25/16.
 **/
public abstract class BaseRxPresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    protected Subscription mSubscription;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
        unsubscribe();
    }

    protected void unsubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        mSubscription = null;
    }

    protected boolean isViewAttached() {
        return mMvpView != null;
    }

    protected T getMvpView() {
        return mMvpView;
    }

    protected void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Chame Presenter.attachView(MvpView) antes de interagir com o Presenter");
        }
    }
}
