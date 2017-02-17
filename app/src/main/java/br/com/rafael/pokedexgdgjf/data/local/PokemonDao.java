package br.com.rafael.pokedexgdgjf.data.local;

import java.util.List;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;

/**
 * Created by rafael on 8/29/16.
 **/
public class PokemonDao {

    public PokemonDao() {

    }

    public boolean saveUpdatePokemon(Pokemon pokemon) {
        try {
            RealmHelper.instanceRealm();
            return RealmHelper.saveObject(pokemon);
        } finally {
            RealmHelper.closeRealm();
        }
    }

    public List<Pokemon> getPokemonsSaved() {
        try {
            RealmHelper.instanceRealm();
            return RealmHelper.findAllSortedBy(Pokemon.class, "id");
        } finally {
            RealmHelper.closeRealm();
        }
    }

    public boolean deletePokemon(Pokemon pokemon) {
        try {
            RealmHelper.instanceRealm();
            Pokemon pokemonDelete =
                    (Pokemon) RealmHelper.findFirstByField(Pokemon.class, "id", pokemon.getId());
            return RealmHelper.delete(pokemonDelete);
        } finally {
            RealmHelper.closeRealm();
        }
    }
}
