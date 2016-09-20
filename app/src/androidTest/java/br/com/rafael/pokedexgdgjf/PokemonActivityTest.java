package br.com.rafael.pokedexgdgjf;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import br.com.rafael.pokedexgdgjf.data.model.Pokemon;
import br.com.rafael.pokedexgdgjf.test.common.TestComponentRule;
import br.com.rafael.pokedexgdgjf.test.common.TestDataFactory;
import br.com.rafael.pokedexgdgjf.ui.pokemon.PokemonActivity;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;


/**
 * Created by rafael on 9/20/16.
 **/
@RunWith(AndroidJUnit4.class)
public class PokemonActivityTest {

    private final TestComponentRule component =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<PokemonActivity> main =
            new ActivityTestRule<PokemonActivity>(PokemonActivity.class, false, false);


    @Rule
    public TestRule chain = RuleChain.outerRule(component).around(main);

    @Test
    public void showPokemonViewsDisplay() {
        Pokemon pokemon = TestDataFactory.newPokemon();
        when(component.getMockDataManager().getPokemon(anyInt()))
                .thenReturn(Observable.just(pokemon));

        Intent intent = PokemonActivity.getStartIntent(
                InstrumentationRegistry.getTargetContext(), pokemon.getId());
        main.launchActivity(intent);

        onView(withId(R.id.iv_pokemon))
                .check(matches(isDisplayed()));
        onView(withText(pokemon.getName()))
                .check(matches(isDisplayed()));
        onView(withText(String.valueOf(pokemon.getWeight())))
                .check(matches(isDisplayed()));
        onView(withText(String.valueOf(pokemon.getHeight())))
                .check(matches(isDisplayed()));
    }
}
