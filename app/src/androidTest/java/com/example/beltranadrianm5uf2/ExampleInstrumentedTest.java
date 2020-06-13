package com.example.beltranadrianm5uf2;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true,
                    true);

    @Test
    public void editTextErrorTest() {
        onView(withId(R.id.editText)).perform(typeText(""));
        onView(withId(R.id.button)).perform(click());
        onView(withText("Numero UF's requerido!")).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void checkBoxErrorTest() {
        onView(withId(R.id.editText)).perform(typeText("8"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.checkBox)).perform(click());
        onView(withId(R.id.button)).perform(click());
        onView(withText("Opcio disponible nomes amb 0 o 1 UF!!!")).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void SuccessTest() {

        onView(withId(R.id.editText)).perform(typeText("1"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.checkBox)).perform(click());
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textView5)).check(matches(withSubstring("Preu: 0")));
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.beltranadrianm5uf2", appContext.getPackageName());
    }
}
