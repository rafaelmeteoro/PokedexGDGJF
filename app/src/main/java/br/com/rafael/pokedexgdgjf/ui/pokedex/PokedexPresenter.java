package br.com.rafael.pokedexgdgjf.ui.pokedex;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.data.DataManager;
import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.ui.base.BaseRxPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexPresenter extends BaseRxPresenter<PokedexContract.View> implements PokedexContract.Presenter {

    protected DataManager mDataManager;

    @Inject
    public PokedexPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void getPokedex() {
        checkViewAttached();
        showProgress();

        unsubscribe();
        mSubscription = mDataManager.getPodedex()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Pokedex>() {
                    @Override
                    public void onCompleted() {
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Erro ao carregar pokedex");
                        hideProgress();
                        showError();
                    }

                    @Override
                    public void onNext(Pokedex pokedex) {
                        if (pokedex != null && pokedex.hasPokemons()) {
                            getMvpView().showPokemonEntries(pokedex.getPokemonEntries());
                        } else {
                            getMvpView().showEmpty();
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
}
