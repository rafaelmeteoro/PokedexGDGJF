package br.com.rafael.pokedexgdgjf.ui.favoritos;

import java.util.List;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.data.DataManager;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.ui.base.BaseRxPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by rafael on 8/29/16.
 **/
public class FavoritosPresenter extends BaseRxPresenter<FavoritosContract.View> implements FavoritosContract.Presenter {

    protected DataManager mDataManager;

    @Inject
    public FavoritosPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void getFavoritos() {
        checkViewAttached();
        showProgress();

        unsubscribe();
        mSubscription = mDataManager.getPokemonsSaved()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Pokemon>>() {
                    @Override
                    public void onCompleted() {
                        hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "Erro ao carregar pokemons");
                        hideProgress();
                        showErro();
                    }

                    @Override
                    public void onNext(List<Pokemon> pokemons) {
                        if (pokemons != null && !pokemons.isEmpty()) {
                            getMvpView().showPokemons(pokemons);
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

    private void showErro() {
        getMvpView().showErro();
    }
}
