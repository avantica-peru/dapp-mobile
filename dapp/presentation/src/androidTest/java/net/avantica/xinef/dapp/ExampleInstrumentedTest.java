package net.avantica.xinef.dapp;

import android.app.Fragment;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import net.avantica.xinef.dapp.view.activity.ProjectDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {


    @Rule
    private ProjectDetailActivity projectDetailActivity;

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("net.avantica.xinef.dapp", appContext.getPackageName());
    }

    @Test
    public void testContainsUserDetailsFragment() {
        Fragment projectDetailFragment = projectDetailActivity.getFragmentManager().findFragmentById(R.id.container);
        assertThat(projectDetailFragment, is(notNullValue()));
    }
}
