package br.com.rafael.pokedexgdgjf;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.com.rafael.pokedexgdgjf.data.model.Pokedex;
import br.com.rafael.pokedexgdgjf.data.model.PokemonEntrie;
import br.com.rafael.pokedexgdgjf.test.common.TestComponentRule;
import br.com.rafael.pokedexgdgjf.test.common.TestDataFactory;
import br.com.rafael.pokedexgdgjf.ui.pokedex.PokedexActivity;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

/**
 * Created by rafael on 9/4/16.
 **/
@RunWith(AndroidJUnit4.class)
public class PokedexActivityTest {

    private final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<PokedexActivity> main =
            new ActivityTestRule<>(PokedexActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void errorViewsDisplayWhenLoadingContentFails() throws InterruptedException {
        when(component.getMockDataManager().getPokedex())
                .thenReturn(Observable.<Pokedex>error(new RuntimeException()));
        main.launchActivity(null);

        onView(withId(R.id.error_view))
                .check(matches(isDisplayed()));
        onView(withText(R.string.activity_pokedex_load_list_error));
    }

    @Test
    public void emptyContentViewsDisplayWhenLoadingContentFails() throws InterruptedException {
        Pokedex pokedex = TestDataFactory.newPokedexPokemonEntriesEmpty();
        when(component.getMockDataManager().getPokedex())
                .thenReturn(Observable.just(pokedex));
        main.launchActivity(null);

        onView(withId(R.id.error_view))
                .check(matches(isDisplayed()));
        onView(withText(R.string.activity_pokedex_load_list_empty));
    }

    @Test
    public void pokemonItemViewsDisplayWhenClickingReload() throws InterruptedException {
        when(component.getMockDataManager().getPokedex())
                .thenReturn(Observable.<Pokedex>error(new RuntimeException()));
        main.launchActivity(null);

        Pokedex pokedex = TestDataFactory.newPokedex();
        when(component.getMockDataManager().getPokedex())
                .thenReturn(Observable.just(pokedex));

        onView(withId(R.id.error_view))
                .perform(click());

        onView(withId(R.id.error_view))
                .check(matches(not(isDisplayed())));

        checkPokemonItemViewsDisplayed(pokedex.getPokemonEntries().get(0), 0);
    }

    @Test
    public void pokemonItemViewsDisplay() throws InterruptedException {
        Pokedex pokedex = TestDataFactory.newPokedex();
        when(component.getMockDataManager().getPokedex())
                .thenReturn(Observable.just(pokedex));
        main.launchActivity(null);

        for (int i = 0; i < pokedex.getPokemonEntries().size(); i++) {
            checkPokemonItemViewsDisplayed(pokedex.getPokemonEntries().get(i), i);
        }
    }

    private void checkPokemonItemViewsDisplayed(PokemonEntrie pokemonEntrie, int position) {
        onView(withId(R.id.recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(position));
        onView(withText(pokemonEntrie.getPokemonSpecies().getName()))
                .check(matches(isDisplayed()));
    }
}
