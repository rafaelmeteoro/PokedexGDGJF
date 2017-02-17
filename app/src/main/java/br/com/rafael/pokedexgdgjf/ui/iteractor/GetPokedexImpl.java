package br.com.rafael.pokedexgdgjf.ui.iteractor;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import br.com.rafael.pokedexgdgjf.injection.module.ApplicationModule;
import rx.Observable;
import rx.Scheduler;

/**
 * Created by rafael on 2/14/17.
 **/

public class GetPokedexImpl extends UseCase implements GetPokedex {

    private final ApiProvider mApiProvider;

    @Inject
    public GetPokedexImpl(@Named(ApplicationModule.JOB_THREAD) Scheduler jobScheduler,
                          @Named(ApplicationModule.MAIN_THREAD) Scheduler mainThreadScheduler,
                          ApiProvider apiProvider) {
        super(jobScheduler, mainThreadScheduler);
        mApiProvider = apiProvider;
    }

    @Override
    public Observable<Pokedex> execute() {
        // Neste exemplo pokedex id é fixo
        int pokedexId = 2;
        return mApiProvider
                .getPokedexService()
                .getPokedex(pokedexId)
                .observeOn(getPostExecutationScheduler())
                .subscribeOn(getJobScheduler());
    }
}
