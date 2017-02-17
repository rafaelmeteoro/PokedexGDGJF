package br.com.rafael.pokedexgdgjf.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by rafael on 2/15/17.
 **/

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
