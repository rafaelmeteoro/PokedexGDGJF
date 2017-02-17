package br.com.rafael.pokedexgdgjf.ui.iteractor;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import rx.Observable;

/**
 * Created by rafael on 2/16/17.
 **/

public interface GetPokemon {
    Observable<Pokemon> execute(int pokemonId);
}
