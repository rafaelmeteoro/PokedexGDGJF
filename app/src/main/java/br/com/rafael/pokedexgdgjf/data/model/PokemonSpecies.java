package br.com.rafael.pokedexgdgjf.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafael on 8/25/16.
 **/
public class PokemonSpecies {

    private static final String NAME = "name";
    private static final String URL = "url";

    @SerializedName(NAME)
    private String name;

    @SerializedName(URL)
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
