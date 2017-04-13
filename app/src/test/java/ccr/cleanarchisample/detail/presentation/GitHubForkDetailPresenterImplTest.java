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
import static org.mockito.BDDMockito.given;
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
        GitHubFork gitHubForkMock = mock(GitHubFork.class);
        GitHubForkDetailPresenter presenter = new GitHubForkDetailPresenterImpl(view);

        given(gitHubForkMock.getTitle()).willReturn("title");
        given(gitHubForkMock.getUrlImage()).willReturn("urlImage");
        given(gitHubForkMock.getDescription()).willReturn("description");


        presenter.presentDetail(gitHubForkMock);

        verify(view).displayDetail(captor.capture());

        GitHubForkDetailViewModel viewModel = captor.getValue();
        assertThat(viewModel).isInstanceOf(GitHubForkDetailViewModel.class);
        assertThat(viewModel.getTitle()).isEqualTo("title");
        assertThat(viewModel.getDescription()).isEqualTo("description");
        assertThat(viewModel.getImageUrl()).isEqualTo("urlImage");
    }

}