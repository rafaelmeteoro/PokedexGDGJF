package br.com.rafael.pokedexgdgjf.data.operator;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;

/**
 * Created by rafael on 8/29/16.
 **/
@Singleton
public class WorkerOperatorProvider {

    private final WorkerOperator<Pokedex> mWorkerOperatorPokedex;
    private final WorkerOperator<Pokemon> mWorkerOperatorPokemon;
    private final WorkerOperator<List<Pokemon>> mWorkerOperatorListPokemon;
    private final WorkerOperator<Boolean> mWorkerOperatorBoolean;

    @Inject
    public WorkerOperatorProvider(
            WorkerOperator<Pokedex> workerOperatorPokedex,
            WorkerOperator<Pokemon> workerOperatorPokemon,
            WorkerOperator<List<Pokemon>> workerOperatorListPokemon,
            WorkerOperator<Boolean> workerOperatorBoolean) {
        mWorkerOperatorPokedex = workerOperatorPokedex;
        mWorkerOperatorPokemon = workerOperatorPokemon;
        mWorkerOperatorListPokemon = workerOperatorListPokemon;
        mWorkerOperatorBoolean = workerOperatorBoolean;
    }

    public WorkerOperator<Pokedex> getWorkerOperatorPokedex() {
        return mWorkerOperatorPokedex;
    }

    public WorkerOperator<Pokemon> getWorkerOperatorPokemon() {
        return mWorkerOperatorPokemon;
    }

    public WorkerOperator<List<Pokemon>> getWorkerOperatorListPokemon() {
        return mWorkerOperatorListPokemon;
    }

    public WorkerOperator<Boolean> getWorkerOperatorBoolean() {
        return mWorkerOperatorBoolean;
    }
}
