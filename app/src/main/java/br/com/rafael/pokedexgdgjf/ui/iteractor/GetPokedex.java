package br.com.rafael.pokedexgdgjf.ui.iteractor;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import rx.Observable;

/**
 * Created by rafael on 2/14/17.
 **/

public interface GetPokedex {
    Observable<Pokedex> execute();
}
