package net.avantica.xinef.dapp.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import net.avantica.xinef.dapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void shouldShowFilterDialog() throws Exception {
        onView(withId(R.id.action_filter)).perform(click());
    }

    @Test
    public void shouldEnterValuesToFilters() {
        onView(withId(R.id.action_filter)).perform(click());

    }

}