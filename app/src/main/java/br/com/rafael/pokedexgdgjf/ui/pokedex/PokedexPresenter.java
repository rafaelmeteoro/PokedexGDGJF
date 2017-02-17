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

    private GetPokedex getPokedex;

    private CompositeSubscription subscriptions;

    @Inject
    public PokedexPresenter(GetPokedex getPokedex) {
        this.getPokedex = getPokedex;

        subscriptions = new CompositeSubscription();
    }

    @Override
    protected void clean() {
        subscriptions.clear();
    }

    @Override
    public void getPokedex() {
        PokedexContract.View view = getView();
        view.showProgress();

        subscriptions.add(
                getPokedex.execute()
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
