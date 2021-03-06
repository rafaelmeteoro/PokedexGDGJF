package br.com.rafael.pokedexgdgjf.ui.favoritos;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.ui.base.BasePresenter;
import br.com.rafael.pokedexgdgjf.ui.iteractor.Repository;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by rafael on 8/29/16.
 **/
public class FavoritosPresenter extends BasePresenter<FavoritosContract.View>
        implements FavoritosContract.Presenter {

    private Repository repository;

    private CompositeSubscription subscriptions;

    @Inject
    public FavoritosPresenter(Repository repository) {
        subscriptions = new CompositeSubscription();

        this.repository = repository;
    }

    @Override
    protected void clean() {
        subscriptions.clear();
    }

    @Override
    public void getFavoritos() {
        FavoritosContract.View view = getView();
        view.showProgress();

        subscriptions.add(
                repository.getPokemonsSaved()
                        .subscribe(pokemons -> {
                            view.hideProgress();
                            if (pokemons != null && !pokemons.isEmpty()) {
                                view.showPokemons(pokemons);
                            } else {
                                view.showEmpty();
                            }
                        }, error -> {
                            Timber.e(error, "Erro ao carregar pokemons");
                            view.hideProgress();
                            view.showErro();
                        })
        );
    }

    @Override
    public void deletePokemon(Pokemon pokemon) {
        FavoritosContract.View view = getView();

        subscriptions.add(
                repository.deletePokemon(pokemon)
                        .subscribe(isDeleted -> {
                            if (isDeleted) {
                                view.showMessage();
                                getFavoritos();
                            }
                        }, error -> {
                            Timber.e(error, "Erro ao deletar pokemon");
                        })
        );
    }
}
