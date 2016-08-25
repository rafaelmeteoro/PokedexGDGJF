package br.com.rafael.pokedexgdgjf.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by rafael on 8/25/16.
 **/
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
