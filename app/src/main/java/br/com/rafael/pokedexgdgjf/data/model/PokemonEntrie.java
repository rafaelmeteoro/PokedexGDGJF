package br.com.rafael.pokedexgdgjf.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafael on 8/25/16.
 **/
public class PokemonEntrie {

    private static final String ENTRY_NUMBER = "entry_number";
    private static final String POKEMON_SPECIES = "pokemon_species";

    @SerializedName(ENTRY_NUMBER)
    private int entryNumber;

    @SerializedName(POKEMON_SPECIES)
    private PokemonSpecies pokemonSpecies;

    public PokemonSpecies getPokemonSpecies() {
        return pokemonSpecies;
    }
}
