package br.com.rafael.pokedexgdgjf.ui.pokemon;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.test.common.TestDataFactory;
import br.com.rafael.pokedexgdgjf.util.RxSchedulersOverrideRule;
import rx.Observable;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

/**
 * Created by rafael on 9/2/16.
 **/
@RunWith(MockitoJUnitRunner.class)
public class PokemonPresenterTest {

    @Mock
    PokemonContract.View mMockView;

    private PokemonPresenter mPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setup() {
        //presenter = new PokemonPresenter(mMockDataManager);
        mPresenter.attachView(mMockView);
    }

    @After
    public void detachView() {
        mPresenter.detachView();
    }

    @Test
    public void getPokemonSuccessful() {
        Pokemon pokemon = TestDataFactory.newPokemon();
        stubDataManagerGetPokemon(Observable.just(pokemon));

        mPresenter.getPokemon(0);
        verify(mMockView).showProgress();
        verify(mMockView).showPokemonName(pokemon.getName());
        verify(mMockView).showPokemonHeight(pokemon.getHeight());
        verify(mMockView).showPokemonWeight(pokemon.getWeight());
        verify(mMockView).showPokemonImage(pokemon.getSprites().getFrontDefault());
        verify(mMockView).showPokemon(pokemon);
        verify(mMockView).hideProgress();
    }

    @Test
    public void getPokemonFailure() {
        stubDataManagerGetPokemon(Observable.<Pokemon>error(new RuntimeException()));

        mPresenter.getPokemon(0);
        verify(mMockView).showProgress();
        verify(mMockView).hideProgress();
        verify(mMockView).showError();
    }

    @Test
    public void savePokemonSuccessful() {
        stubDataManagerSavePokemon(Observable.just(true));

        mPresenter.savePokmon((Pokemon) anyObject());
        //verify(mMockView).showMessage(anyInt());
    }

    private void stubDataManagerGetPokemon(Observable<Pokemon> observable) {
        //when(mMockDataManager.getPokemon(anyInt())).thenReturn(observable);
    }

    private void stubDataManagerSavePokemon(Observable<Boolean> observable) {
        //when(mMockDataManager.saveUpdatePokemon((Pokemon) anyObject())).thenReturn(observable);
    }
}
