package net.avantica.xinef.dapp.data;

import net.avantica.xinef.dapp.domain.entity.PublicInvestmentProject;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static final String FAKE_PROJECT_UNIQUE_CODE = "1";

    private PublicInvestmentProject publicInvestmentProject;

    @Before
    public void setUp() {
        publicInvestmentProject = new PublicInvestmentProject();
        publicInvestmentProject.setUniqueCode(FAKE_PROJECT_UNIQUE_CODE);
    }

    @Test
    public void testPublicInvestmentProject1() {
        String uniqueCode = publicInvestmentProject.getUniqueCode();
        assertThat(uniqueCode, is(FAKE_PROJECT_UNIQUE_CODE));
    }
}