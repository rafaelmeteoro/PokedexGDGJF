package br.com.rafael.pokedexgdgjf.ui.pokedex;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.test.common.TestDataFactory;
import br.com.rafael.pokedexgdgjf.util.RxSchedulersOverrideRule;
import rx.Observable;

import static org.mockito.Mockito.verify;

/**
 * Created by rafael on 9/2/16.
 **/
@RunWith(MockitoJUnitRunner.class)
public class PokedexPresenterTest {

    @Mock
    PokedexContract.View mMockView;

    private PokedexPresenter mPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setup() {
        /*presenter = new PokedexPresenter(mMockDataManager);
        presenter.attachView(mMockView);*/
    }

    @After
    public void detachView() {
        mPresenter.detachView();
    }

    @Test
    public void getPokedexSucessful() {
        Pokedex pokedex = TestDataFactory.newPokedex();
        stubDataManagerGetPokedex(Observable.just(pokedex));

        mPresenter.getPokedex();
        verify(mMockView).showProgress();
        verify(mMockView).showPokemonEntries(pokedex.getPokemonEntries());
        verify(mMockView).hideProgress();
    }

    @Test
    public void getPokedexPokemonEntriesEmpty() {
        Pokedex pokedex = TestDataFactory.newPokedexPokemonEntriesEmpty();
        stubDataManagerGetPokedex(Observable.just(pokedex));

        mPresenter.getPokedex();
        verify(mMockView).showProgress();
        verify(mMockView).showEmpty();
        verify(mMockView).hideProgress();
    }

    @Test
    public void getPokedexFailure() {
        stubDataManagerGetPokedex(Observable.<Pokedex>error(new RuntimeException()));

        mPresenter.getPokedex();
        verify(mMockView).showProgress();
        verify(mMockView).showError();
    }

    private void stubDataManagerGetPokedex(Observable<Pokedex> observable) {
        //when(mMockDataManager.getPokedex()).thenReturn(observable);
    }
}
