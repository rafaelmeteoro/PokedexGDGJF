package br.com.rafael.pokedexgdgjf.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafael on 8/28/16.
 **/
public class Pokemon {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String SPRITES = "sprites";

    @SerializedName(ID)
    private int id;

    @SerializedName(NAME)
    private String name;

    @SerializedName(WEIGHT)
    private int weight;

    @SerializedName(HEIGHT)
    private int height;

    @SerializedName(SPRITES)
    private Sprites sprites;
}
