package br.com.rafael.pokedexgdgjf.ui.pokemon;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.ui.base.BasePresenter;
import br.com.rafael.pokedexgdgjf.ui.iteractor.GetPokemon;
import br.com.rafael.pokedexgdgjf.ui.iteractor.Repository;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by rafael on 8/29/16.
 **/
public class PokemonPresenter extends BasePresenter<PokemonContract.View>
        implements PokemonContract.Presenter {

    private GetPokemon mGetPokemon;
    private Repository mRepository;

    private CompositeSubscription mSubscriptions;

    @Inject
    public PokemonPresenter(GetPokemon getPokemon, Repository repository) {
        mGetPokemon = getPokemon;
        mRepository = repository;

        mSubscriptions = new CompositeSubscription();
    }

    @Override
    protected void clean() {
        mSubscriptions.clear();
    }

    @Override
    public void getPokemon(int pokemonId) {
        PokemonContract.View view = getView();
        view.showProgress();

        mSubscriptions.add(
                mGetPokemon.execute(pokemonId)
                        .subscribe(pokemon -> {
                            view.showPokemonName(pokemon.getName());
                            view.showPokemonHeight(pokemon.getHeight());
                            view.showPokemonWeight(pokemon.getWeight());
                            view.showPokemonImage(pokemon.getSprites().getFrontDefault());
                            view.showPokemon(pokemon);
                            view.hideProgress();
                        }, error -> {
                            Timber.e(error, "Erro ao carregar PokemonId: " + pokemonId);
                            view.hideProgress();
                            view.showError();
                        })
        );
    }

    @Override
    public void savePokmon(Pokemon pokemon) {
        PokemonContract.View view = getView();

        mSubscriptions.add(
                mRepository.saveUpdatePokemon(pokemon)
                        .subscribe(isSaved -> {
                            if (isSaved) {
                                view.showMessage();
                            }
                        })
        );
    }
}
