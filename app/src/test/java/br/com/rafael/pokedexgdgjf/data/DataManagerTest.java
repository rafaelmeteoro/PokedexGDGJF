package br.com.rafael.pokedexgdgjf.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Inject;

import br.com.rafael.pokedexgdgjf.BuildConfig;
import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import br.com.rafael.pokedexgdgjf.test.common.TestDataFactory;
import br.com.rafael.pokedexgdgjf.util.RxSchedulersOverrideRule;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by rafael on 8/30/16.
 **/
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock
    PokemonDao mMockPokemonDao;

    @InjectMocks
    ApiProvider mMockApiProvider = spy(new ApiProvider(mockRetrofit()));

    DataManager mDataManager;

    @Rule
    // Must be added to every test class that targets app code that use RxJava
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    private Retrofit mockRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Before
    public void setup() {
        mDataManager = new DataManager(mMockApiProvider, mMockPokemonDao);
    }

    @Test
    public void getPokedexComplete() {
        Pokedex pokedex = TestDataFactory.newPokedex();
        stubPokexServiceGetPokedex(Observable.just(pokedex), 0);

        TestSubscriber<Pokedex> testSubscriber = new TestSubscriber<>();
        mDataManager.getPokedex().subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(pokedex);
    }

    private void stubPokexServiceGetPokedex(Observable<Pokedex> observable, int id) {
        //when(mMockApiProvider.getPokedexService().getPokedex(id)).thenReturn(observable);
        doReturn(observable).when(mMockApiProvider).getPokedexService().getPokedex(id);
    }
}
