package br.com.rafael.pokedexgdgjf.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rafael on 8/28/16.
 **/
public class Sprites {

    private static final String BACK_FEMALE = "back_female";
    private static final String BACK_SHINY_FEMALE = "back_shiny_female";
    private static final String BACK_DEFAULT = "back_default";
    private static final String FRONT_FEMALE = "front_female";
    private static final String FRONT_SHINY_FEMALE = "front_shiny_female";
    private static final String BACK_SHINY = "back_shiny";
    private static final String FRONT_DEFAULT = "front_default";
    private static final String FRONT_SHINY = "front_shiny";

    @SerializedName(BACK_FEMALE)
    private String backFemale;

    @SerializedName(BACK_SHINY_FEMALE)
    private String backShinyFemale;

    @SerializedName(BACK_DEFAULT)
    private String backDefault;

    @SerializedName(FRONT_FEMALE)
    private String frontFemale;

    @SerializedName(FRONT_SHINY_FEMALE)
    private String frontShinyFemale;

    @SerializedName(BACK_SHINY)
    private String backShiny;

    @SerializedName(FRONT_DEFAULT)
    private String frontDefault;

    @SerializedName(FRONT_SHINY)
    private String frontShiny;
}
