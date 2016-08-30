package br.com.rafael.pokedexgdgjf.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rafael on 8/25/16.
 **/
public class Pokedex {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String POKEMON_ENTRIES = "pokemon_entries";

    @SerializedName(ID)
    private int id;

    @SerializedName(NAME)
    private String name;

    @SerializedName(POKEMON_ENTRIES)
    private List<PokemonEntrie> pokemonEntries;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonEntrie> getPokemonEntries() {
        return pokemonEntries;
    }

    public void setPokemonEntries(List<PokemonEntrie> pokemonEntries) {
        this.pokemonEntries = pokemonEntries;
    }

    public boolean hasPokemons() {
        return pokemonEntries != null && !pokemonEntries.isEmpty();
    }
}
