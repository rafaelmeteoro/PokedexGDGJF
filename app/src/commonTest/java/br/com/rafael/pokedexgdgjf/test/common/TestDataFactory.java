package br.com.rafael.pokedexgdgjf.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import br.com.rafael.pokedexgdgjf.data.model.PokemonSpecies;

/**
 * Created by rafael on 8/30/16.
 **/
public class TestDataFactory {

    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static int randomInt() {
        Random random = new Random();
        return random.nextInt(10000);
    }

    public static PokemonSpecies newPokemonSpecies() {
        PokemonSpecies pokemonSpecies = new PokemonSpecies();
        pokemonSpecies.setName(randomString());
        pokemonSpecies.setUrl(randomString());
        return pokemonSpecies;
    }

    public static PokemonEntrie newPokemonEntrie() {
        PokemonEntrie pokemonEntrie = new PokemonEntrie();
        pokemonEntrie.setEntryNumber(randomInt());
        pokemonEntrie.setPokemonSpecies(newPokemonSpecies());
        return pokemonEntrie;
    }

    public static List<PokemonEntrie> newPokemonEntrieList(int size) {
        List<PokemonEntrie> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(newPokemonEntrie());
        }
        return list;
    }

    public static Pokedex newPokedex() {
        Pokedex pokedex = new Pokedex();
        pokedex.setId(randomInt());
        pokedex.setName(randomString());
        pokedex.setPokemonEntries(newPokemonEntrieList(10));
        return pokedex;
    }
}
