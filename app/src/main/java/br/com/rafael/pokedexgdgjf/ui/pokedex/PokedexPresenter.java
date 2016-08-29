package br.com.rafael.pokedexgdgjf.ui.pokedex;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.data.DataManager;
import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.ui.base.BaseRxPresenter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

        unsubscribe();
        mSubscription = mDataManager.getPodedex()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Pokedex>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String b = "";
                    }

                    @Override
                    public void onNext(Pokedex pokedex) {
                        String b = "";
                    }
                });
    }
}
