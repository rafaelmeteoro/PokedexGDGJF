package br.com.rafael.pokedexgdgjf.ui.iteractor;

import java.util.List;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import rx.Observable;

/**
 * Created by rafael on 2/16/17.
 **/

public interface Repository {
    Observable<List<Pokemon>> getPokemonsSaved();

    Observable<Boolean> saveUpdatePokemon(Pokemon pokemon);

    Observable<Boolean> deletePokemon(Pokemon pokemon);
}
