package br.com.rafael.pokedexgdgjf.injection.module;

import java.util.List;

import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.data.operator.WorkerOperator;
import dagger.Module;
import dagger.Provides;

/**
 * Created by rafael on 8/29/16.
 **/
@Module
public class RxJavaModule {

    private final WorkerOperator workerOperator;

    public RxJavaModule() {
        workerOperator = new WorkerOperator();
    }

    @Provides
    @Singleton
    WorkerOperator<Pokedex> provideWorkerOperatorPokedex() {
        return workerOperator;
    }

    @Provides
    @Singleton
    WorkerOperator<Pokemon> provideWorkerOperatorPokemon() {
        return workerOperator;
    }

    @Provides
    @Singleton
    WorkerOperator<List<Pokemon>> provideWorkerOperatorListPokemon() {
        return workerOperator;
    }

    @Provides
    @Singleton
    WorkerOperator<Boolean> provideWorkerOperatorBoolean() {
        return workerOperator;
    }
}
