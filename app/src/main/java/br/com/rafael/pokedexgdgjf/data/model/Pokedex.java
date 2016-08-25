package br.com.rafael.pokedexgdgjf.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rafael on 8/25/16.
 **/
public class Pokedex {

    private static final String NAME = "name";
    private static final String POKEMON_ENTRIES = "pokemon_entries";

    @SerializedName(NAME)
    private String name;

    @SerializedName(POKEMON_ENTRIES)
    private List<PokemonEntrie> pokemonEntries;
}
