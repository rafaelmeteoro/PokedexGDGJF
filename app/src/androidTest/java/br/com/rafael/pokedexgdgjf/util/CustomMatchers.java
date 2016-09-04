package br.com.rafael.pokedexgdgjf.util;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by rafael on 9/3/16.
 **/
public class CustomMatchers {

    public static Matcher<View> hasCompoundDrawableRelative(final boolean start,
                                                            final boolean top,
                                                            final boolean end,
                                                            final boolean bottom) {

        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                if (item instanceof TextView) {
                    TextView textView = (TextView) item;
                    Drawable[] drawables = textView.getCompoundDrawables();
                    boolean hasStart = drawables[0] != null;
                    boolean hasStop = drawables[1] != null;
                    boolean hasEnd = drawables[2] != null;
                    boolean hasBottom = drawables[3] != null;
                    return start == hasStart &&
                            top == hasStop &&
                            end == hasEnd &&
                            bottom == hasBottom;
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("CompoundDrawables relative not matched");
            }
        };
    }
}
