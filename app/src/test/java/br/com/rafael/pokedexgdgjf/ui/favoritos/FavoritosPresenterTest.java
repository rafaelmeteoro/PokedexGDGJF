package br.com.rafael.pokedexgdgjf.ui.favoritos;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import br.com.rafael.pokedexgdgjf.data.DataManager;
import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.test.common.TestDataFactory;
import br.com.rafael.pokedexgdgjf.util.RxSchedulersOverrideRule;
import rx.Observable;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rafael on 9/2/16.
 **/
@RunWith(MockitoJUnitRunner.class)
public class FavoritosPresenterTest {

    @Mock
    FavoritosContract.View mMockView;

    @Mock
    DataManager mMockDataManager;

    private FavoritosPresenter mPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setup() {
        //mPresenter = new FavoritosPresenter(mMockDataManager);
        mPresenter.attachView(mMockView);
    }

    @After
    public void detachView() {
        mPresenter.detachView();
    }

    @Test
    public void getFavoritosSuccessful() {
        List<Pokemon> pokemonList = TestDataFactory.newPokemonList(10);
        stubDataManagerGetFavoritos(Observable.just(pokemonList));

        mPresenter.getFavoritos();
        verify(mMockView).showProgress();
        verify(mMockView).showPokemons(pokemonList);
        verify(mMockView).hideProgress();
    }

    @Test
    public void getFavoritosEmpty() {
        List<Pokemon> pokemonList = new ArrayList<>();
        stubDataManagerGetFavoritos(Observable.just(pokemonList));

        mPresenter.getFavoritos();
        verify(mMockView).showProgress();
        verify(mMockView).showEmpty();
        verify(mMockView).hideProgress();
    }

    @Test
    public void getFavoritosFailure() {
        stubDataManagerGetFavoritos(Observable.<List<Pokemon>>error(new RuntimeException()));

        mPresenter.getFavoritos();
        verify(mMockView).showProgress();
        verify(mMockView).hideProgress();
        verify(mMockView).showErro();
    }

    @Test
    public void deletePokemonSuccessful() {
        List<Pokemon> pokemonList = TestDataFactory.newPokemonList(10);
        stubDataManagerDeletePokemon(Observable.just(true));
        stubDataManagerGetFavoritos(Observable.just(pokemonList));

        mPresenter.deletePokemon((Pokemon) anyObject());
        //verify(mMockView).showMessage(anyInt());
        verify(mMockView).showProgress();
        verify(mMockView).showPokemons(pokemonList);
        verify(mMockView).hideProgress();
    }

    private void stubDataManagerGetFavoritos(Observable<List<Pokemon>> observable) {
        when(mMockDataManager.getPokemonsSaved()).thenReturn(observable);
    }

    private void stubDataManagerDeletePokemon(Observable<Boolean> observable) {
        when(mMockDataManager.deletePokemon((Pokemon) anyObject())).thenReturn(observable);
    }
}
