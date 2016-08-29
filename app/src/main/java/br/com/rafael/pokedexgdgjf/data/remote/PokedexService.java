package br.com.rafael.pokedexgdgjf.data.remote;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rafael on 8/28/16.
 **/
public interface PokedexService {

    @GET("pokedex/{id}")
    Observable<Pokedex> getPokedex(
            @Path("id") int id
    );

    @GET("pokemon/{id}")
    Observable<Pokemon> getPokemon(
            @Path("id") int id
    );
}
