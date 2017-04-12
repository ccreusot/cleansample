package ccr.cleanarchisample.list.core;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ccr.cleanarchisample.core.GitHubRepository;
import ccr.cleanarchisample.list.presentation.GitHubForkListPresenter;
import ccr.cleanarchisample.model.GitHubFork;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GitHubForkListInteractorImplTest {

    private GitHubForkListInteractor interactor;
    private GitHubRepository repository;
    private GitHubForkListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = mock(GitHubForkListPresenter.class);
        repository = mock(GitHubRepository.class);
        interactor = new GitHubForkListInteractorImpl(presenter, repository);
    }

    @Test
    public void fetchForkList_WithException() throws Exception {
        doThrow(GitHubRepository.GitHubRepositoryException.class).when(repository).getForkList();

        interactor.fetchForkList();

        verify(repository).getForkList();
        verify(presenter).presentError();
    }

    @Test
    public void fetchForkList_WithListIsEmpty() throws Exception {
        List<GitHubFork> gitHubForks = Collections.emptyList();
        given(repository.getForkList()).willReturn(gitHubForks);

        interactor.fetchForkList();

        verify(repository).getForkList();
        verify(presenter).presentNothing();
    }

    @Test
    public void fetchForkList() throws Exception {
        List<GitHubFork> gitHubForks = Arrays.asList(
                mock(GitHubFork.class),
                mock(GitHubFork.class)
        );
        given(repository.getForkList()).willReturn(gitHubForks);

        interactor.fetchForkList();

        verify(repository).getForkList();
        verify(presenter).presentList(gitHubForks);
    }
}