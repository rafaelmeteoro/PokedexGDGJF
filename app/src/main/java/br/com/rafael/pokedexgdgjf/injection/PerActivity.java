package br.com.rafael.pokedexgdgjf.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by rafael on 8/25/16.
 **/
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
