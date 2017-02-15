package br.com.rafael.pokedexgdgjf.ui.pokedex;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.ui.base.BasePresenter;
import br.com.rafael.pokedexgdgjf.ui.iteractor.GetPokedex;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by rafael on 8/28/16.
 **/
public class PokedexPresenter extends BasePresenter<PokedexContract.View>
        implements PokedexContract.Presenter {

    private GetPokedex mGetPokedex;

    private CompositeSubscription mSubscriptions;

    @Inject
    public PokedexPresenter(GetPokedex getPokedex) {
        mGetPokedex = getPokedex;

        mSubscriptions = new CompositeSubscription();
    }

    @Override
    protected void clean() {
        mSubscriptions.clear();
    }

    @Override
    public void getPokedex() {
        PokedexContract.View view = getView();
        view.showProgress();

        mSubscriptions.add(
                mGetPokedex.execute()
                        .subscribe(
                                pokedex -> {
                                    if (pokedex != null && pokedex.hasPokemons()) {
                                        view.showPokemonEntries(pokedex.getPokemonEntries());
                                    } else {
                                        view.showEmpty();
                                    }
                                    view.hideProgress();
                                }, error -> {
                                    Timber.e(error, "Erro ao carregar pokedex");
                                    view.hideProgress();
                                    view.showError();
                                }
                        )
        );
    }
}
