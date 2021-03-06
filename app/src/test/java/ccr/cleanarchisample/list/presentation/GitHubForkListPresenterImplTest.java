package ccr.cleanarchisample.list.presentation;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

import ccr.cleanarchisample.model.GitHubFork;
import ccr.cleanarchisample.list.viewmodel.GitHubForkItemViewModel;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GitHubForkListPresenterImplTest {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private GitHubForkListPresenter presenter;
    private GitHubForkListView view;

    @Captor
    private ArgumentCaptor<List<GitHubForkItemViewModel>> listCaptor;

    @Before
    public void setUp() throws Exception {
        view = mock(GitHubForkListView.class);
        presenter = new GitHubForkListPresenterImpl(view);
    }

    @Test
    public void testPresentNothing() throws Exception {
        presenter.presentNothing();

        verify(view).displayNothing();
    }

    @Test
    public void testPresentError() throws Exception {
        presenter.presentError();

        verify(view).displayError();
    }

    @Test
    public void testPresentList() throws Exception {
        GitHubFork gitHubForkMock = mock(GitHubFork.class);
        List<GitHubFork> gitHubForkList = Arrays.asList(
            gitHubForkMock,
            mock(GitHubFork.class)
        );

        given(gitHubForkMock.getId()).willReturn("id");
        given(gitHubForkMock.getTitle()).willReturn("title");
        given(gitHubForkMock.getUrlImage()).willReturn("urlImage");

        presenter.presentList(gitHubForkList);

        verify(view).displayList(listCaptor.capture());

        GitHubForkItemViewModel gitHubForkItemViewModel = listCaptor.getValue().get(0);
        assertThat(gitHubForkItemViewModel).isInstanceOf(GitHubForkItemViewModel.class);
        assertThat(gitHubForkItemViewModel.getId()).isEqualTo("id");
        assertThat(gitHubForkItemViewModel.getTitle()).isEqualTo("title");
        assertThat(gitHubForkItemViewModel.getUrlImage()).isEqualTo("urlImage");
    }
}