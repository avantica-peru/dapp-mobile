package net.avantica.xinef.dapp.domain.interactor;

import net.avantica.xinef.dapp.domain.executor.PostExecutionThread;
import net.avantica.xinef.dapp.domain.executor.ThreadExecutor;
import net.avantica.xinef.dapp.domain.repository.PublicInvestmentProjectRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetPublicInvestmentProjectListTest {

    public static final boolean CLOUD = true;
    @Mock
    private PublicInvestmentProjectRepository publicInvestmentProjectRepository;
    @Mock
    private ThreadExecutor threadExecutor;
    @Mock
    private PostExecutionThread postExecutionThread;
    private GetPublicInvestmentProjectList getPublicInvestmentProjectList;

    @Before
    public void setUp() {
        getPublicInvestmentProjectList = new GetPublicInvestmentProjectList(CLOUD, publicInvestmentProjectRepository, null, null);
    }

    @Test
    public void shouldSendToTheRepositoryWhenBuildingUseCaseObservable() {
        getPublicInvestmentProjectList.buildUseCaseObservable();

        verify(publicInvestmentProjectRepository).publicInvestmentProjects(CLOUD);
        verifyNoMoreInteractions(publicInvestmentProjectRepository);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }
}