package net.avantica.xinef.dapp.view.activity;


import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import net.avantica.xinef.dapp.R;
import net.avantica.xinef.dapp.model.UbigeoModel;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;

@RunWith(AndroidJUnit4.class)
public class FilterDialogFragmentTest {
    public static final String VALID_DEPARTMENT_NAME = "LIMA";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

//    @Test
//    public void testLabelUpdatesIfValidCountrySelected() {
//        onView(withId(R.id.action_filter))
//                .perform(click());
//        // Click on the Spinner
//        onView(withId(R.id.sp_department)).perform(click());
//
//        // Select a country from the list
//        onData(allOf(is(instanceOf(String.class)), is(VALID_DEPARTMENT_NAME))).perform(click());
//
//        // Check that the country label is updated with selected country
//        onView(withId(R.id.sp_department)).check(matches(withText(VALID_DEPARTMENT_NAME)));
//    }

    @Test
    public void filterDialogFragmentTest() {
        onView(withId(R.id.action_filter))
                .perform(click());

        onView(withId(R.id.sp_department))
                .perform(click());

//        onData(allOf(is(instanceOf(String.class)), is("LIMA"))).perform(click());
//        onData(withMyValue("CALLAO")).perform(click());
//        onData(hasToString(startsWith("LIMA"))).inAdapterView(withId(R.id.sp_department)).perform(click());

        onData(allOf(is(instanceOf(String.class)))).atPosition(1).perform(click());

//        onView(withId(R.id.sp_department)).check(matches(withSpinnerText(containsString(""))))
//
//        ViewInteraction appCompatCheckedTextView = onView(
//                allOf(withId(android.R.id.text1), withText("LIMA"), isDisplayed()));
//        appCompatCheckedTextView.perform(click());

    }

    public static Matcher<Object> withMyValue(final String expectedName) {
        return new BoundedMatcher<Object, UbigeoModel>(UbigeoModel.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with expectedName: " + expectedName);
            }

            @Override
            protected boolean matchesSafely(UbigeoModel myValue) {
                return myValue.getDescripcion().equalsIgnoreCase(expectedName);
            }
        };
    }
}
