package br.com.rafael.pokedexgdgjf.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import br.com.rafael.pokedexgdgjf.data.model.PokemonSpecies;
import br.com.rafael.pokedexgdgjf.data.model.Sprites;

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

    public static Pokedex newPokedexPokemonEntriesEmpty() {
        Pokedex pokedex = new Pokedex();
        pokedex.setId(randomInt());
        pokedex.setName(randomString());
        pokedex.setPokemonEntries(new ArrayList<PokemonEntrie>());
        return pokedex;
    }

    public static Sprites newSprites() {
        Sprites sprites = new Sprites();
        sprites.setBackDefault(randomString());
        sprites.setBackFemale(randomString());
        sprites.setBackShiny(randomString());
        sprites.setBackShinyFemale(randomString());
        sprites.setFrontDefault(randomString());
        sprites.setFrontFemale(randomString());
        sprites.setFrontShiny(randomString());
        sprites.setFrontShinyFemale(randomString());
        return sprites;
    }

    public static Pokemon newPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setId(randomInt());
        pokemon.setName(randomString());
        pokemon.setWeight(randomInt());
        pokemon.setHeight(randomInt());
        pokemon.setSprites(newSprites());
        return pokemon;
    }

    public static List<Pokemon> newPokemonList(int size) {
        List<Pokemon> pokemonList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            pokemonList.add(newPokemon());
        }
        return pokemonList;
    }
}
