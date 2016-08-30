package br.com.rafael.pokedexgdgjf.data;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.rafael.pokedexgdgjf.data.local.PokemonDao;
import br.com.rafael.pokedexgdgjf.data.remote.ApiProvider;
import br.com.rafael.pokedexgdgjf.util.RxSchedulersOverrideRule;

/**
 * Created by rafael on 8/30/16.
 **/
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock
    ApiProvider mMockApiProvider;

    @Mock
    PokemonDao mMockPokemonDao;

    DataManager mDataManager;

    @Rule
    // Must be added to every test class that targets app code that use RxJava
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setup() {
        mDataManager = new DataManager(mMockApiProvider, mMockPokemonDao);
    }
}
