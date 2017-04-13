package ccr.cleanarchisample.detail.presentation;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import ccr.cleanarchisample.detail.viewmodel.GitHubForkDetailViewModel;
import ccr.cleanarchisample.model.GitHubFork;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GitHubForkDetailPresenterImplTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Captor
    private ArgumentCaptor<GitHubForkDetailViewModel> captor;

    @Test
    public void testPresentNothing() throws Exception {
        GitHubForkDetailView view = mock(GitHubForkDetailView.class);
        GitHubForkDetailPresenter presenter = new GitHubForkDetailPresenterImpl(view);

        presenter.presentNothing();

        verify(view).displayNothing();
    }

    @Test
    public void testPresentError() throws Exception {
        GitHubForkDetailView view = mock(GitHubForkDetailView.class);
        GitHubForkDetailPresenter presenter = new GitHubForkDetailPresenterImpl(view);

        presenter.presentError();

        verify(view).displayError();
    }

    @Test
    public void testPresentDetail() throws Exception {
        GitHubForkDetailView view = mock(GitHubForkDetailView.class);
        GitHubFork gitHubFork = mock(GitHubFork.class);
        GitHubForkDetailPresenter presenter = new GitHubForkDetailPresenterImpl(view);

        presenter.presentDetail(gitHubFork);

        verify(view).displayDetail(captor.capture());
        assertThat(captor.getValue()).isInstanceOf(GitHubForkDetailViewModel.class);
    }

}