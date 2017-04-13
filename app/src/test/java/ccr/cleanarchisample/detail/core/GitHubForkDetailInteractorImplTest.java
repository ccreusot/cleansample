package ccr.cleanarchisample.detail.core;

import org.junit.Before;
import org.junit.Test;

import ccr.cleanarchisample.core.GitHubRepository;
import ccr.cleanarchisample.detail.presentation.GitHubForkDetailPresenter;
import ccr.cleanarchisample.model.GitHubFork;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GitHubForkDetailInteractorImplTest {

    private GitHubForkDetailPresenter presenter;
    private GitHubRepository repository;
    private GitHubForkDetailInteractor interactor;

    @Before
    public void setUp() throws Exception {
        presenter = mock(GitHubForkDetailPresenter.class);
        repository = mock(GitHubRepository.class);
        interactor = new GitHubForkDetailInteractorImpl(presenter, repository);
    }

    @Test
    public void testFetchGitHubForkDetail_WithIdIsNull() throws Exception {
        doThrow(GitHubRepository.GitHubRepositoryException.class).when(repository).getForkDetail(null);

        interactor.fetchGitHubForkDetail(null);

        verify(presenter).presentError();
        verify(repository).getForkDetail(null);
    }


    @Test
    public void testFetchGitHubForkDetail_WithDetailNull() throws Exception {
        given(repository.getForkDetail("id")).willReturn(null);

        interactor.fetchGitHubForkDetail("id");

        verify(presenter).presentNothing();
        verify(repository).getForkDetail("id");
    }

    @Test
    public void testFetchGitHubForkDetail() throws Exception {
        GitHubFork gitHubFork = mock(GitHubFork.class);
        given(repository.getForkDetail("id")).willReturn(gitHubFork);

        interactor.fetchGitHubForkDetail("id");

        verify(presenter).presentDetail(gitHubFork);
        verify(repository).getForkDetail("id");
    }
}