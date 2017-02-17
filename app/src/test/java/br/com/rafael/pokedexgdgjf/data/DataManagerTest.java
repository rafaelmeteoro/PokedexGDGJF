package br.com.rafael.pokedexgdgjf.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import br.com.rafael.pokedexgdgjf.BuildConfig;
import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import br.com.rafael.pokedexgdgjf.data.remote.PokedexService;
import br.com.rafael.pokedexgdgjf.test.common.TestDataFactory;
import br.com.rafael.pokedexgdgjf.util.RxSchedulersOverrideRule;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 * Created by rafael on 8/30/16.
 **/
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock
    PokemonDao mMockPokemonDao;

    @Mock
    PokedexService mMockPokedexService;

    /*@InjectMocks
    ApiProvider mMockApiProvider = spy(new ApiProvider(mockRetrofit()));*/

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

    /*@Before
    public void setup() {
        mDataManager = new DataManager(mMockApiProvider, mMockPokemonDao);
    }*/

    @Test
    public void getPokedexComplete() {
        Pokedex pokedex = TestDataFactory.newPokedex();
        stubPokedexServiceGetPokedex(Observable.just(pokedex));

        TestSubscriber<Pokedex> testSubscriber = new TestSubscriber<>();
        mDataManager.getPokedex().subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(pokedex);
    }

    @Test
    public void getPokedexError() {
        Throwable throwable = new RuntimeException();
        stubPokedexServiceGetPokedex(Observable.<Pokedex>error(throwable));

        TestSubscriber<Pokedex> testSubscriber = new TestSubscriber<>();
        mDataManager.getPokedex().subscribe(testSubscriber);
        testSubscriber.assertNotCompleted();
        testSubscriber.assertError(throwable);
    }

    @Test
    public void getPokemonComplete() {
        Pokemon pokemon = TestDataFactory.newPokemon();
        stubPokedexServiceGetPokemon(Observable.just(pokemon));

        TestSubscriber<Pokemon> testSubscriber = new TestSubscriber<>();
        mDataManager.getPokemon(0).subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(pokemon);
    }

    @Test
    public void getPokemonError() {
        Throwable throwable = new RuntimeException();
        stubPokedexServiceGetPokemon(Observable.<Pokemon>error(throwable));

        TestSubscriber<Pokemon> testSubscriber = new TestSubscriber<>();
        mDataManager.getPokemon(0).subscribe(testSubscriber);
        testSubscriber.assertNotCompleted();
        testSubscriber.assertError(throwable);
    }

    @Test
    public void getPokemonSavedComplete() {
        List<Pokemon> pokemonList = TestDataFactory.newPokemonList(10);
        stubPokemonDaoGetPokemonList(pokemonList);

        TestSubscriber<List<Pokemon>> testSubscriber = new TestSubscriber<>();
        mDataManager.getPokemonsSaved().subscribe(testSubscriber);
        testSubscriber.assertCompleted();
        testSubscriber.assertValue(pokemonList);
    }

    @Test
    public void saveUpdatePokemonComplete() {
        Pokemon pokemon = TestDataFactory.newPokemon();
        stubPokemonDaoSaveUpdatePokemon(pokemon);

        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mDataManager.saveUpdatePokemon(pokemon);
        testSubscriber.assertNoValues();
    }

    @Test
    public void deletePokemonComplete() {
        Pokemon pokemon = TestDataFactory.newPokemon();
        stubPokemonDaoDeletePokemon(pokemon);

        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
        mDataManager.deletePokemon(pokemon);
        testSubscriber.assertNoValues();
    }

    private void stubPokedexServiceGetPokedex(Observable<Pokedex> observable) {
        //when(mMockApiProvider.getPokedexService()).thenReturn(mMockPokedexService);
        when(mMockPokedexService.getPokedex(anyInt())).thenReturn(observable);
    }

    private void stubPokedexServiceGetPokemon(Observable<Pokemon> observable) {
        //when(mMockApiProvider.getPokedexService()).thenReturn(mMockPokedexService);
        when(mMockPokedexService.getPokemon(anyInt())).thenReturn(observable);
    }

    private void stubPokemonDaoGetPokemonList(List<Pokemon> pokemonList) {
        when(mMockPokemonDao.getPokemonsSaved()).thenReturn(pokemonList);
    }

    private void stubPokemonDaoSaveUpdatePokemon(Pokemon pokemon) {
        when(mMockPokemonDao.saveUpdatePokemon(pokemon)).thenReturn(anyBoolean());
    }

    private void stubPokemonDaoDeletePokemon(Pokemon pokemon) {
        when(mMockPokemonDao.deletePokemon(pokemon)).thenReturn(anyBoolean());
    }
}
