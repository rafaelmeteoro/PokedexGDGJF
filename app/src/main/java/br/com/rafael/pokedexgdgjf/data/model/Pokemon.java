package br.com.rafael.pokedexgdgjf.data.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rafael on 8/28/16.
 **/
public class Pokemon extends RealmObject {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String WEIGHT = "weight";
    private static final String HEIGHT = "height";
    private static final String SPRITES = "sprites";

    @PrimaryKey
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public Sprites getSprites() {
        return sprites;
    }
}
