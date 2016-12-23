package net.avantica.xinef.dapp.domain.interactor;

import net.avantica.xinef.dapp.domain.repository.PublicInvestmentProjectRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GetPublicInvestmentProjectDetailsTest {
    public static final String PROJECT_ID = "5";
    @Mock
    private PublicInvestmentProjectRepository publicInvestmentProjectRepository;
    private GetPublicInvestmentProjectDetails getPublicInvestmentProjectDetails;


    @Before
    public void setUp() {
        getPublicInvestmentProjectDetails = new GetPublicInvestmentProjectDetails(
                PROJECT_ID, publicInvestmentProjectRepository, null, null);
    }

    @Test
    public void shouldSendTheProjectIdToTheRepositoryWhenBuildingUseCaseObservable() {
        getPublicInvestmentProjectDetails.buildUseCaseObservable();
        verify(publicInvestmentProjectRepository).publicInvestmentProject(PROJECT_ID);
    }

}