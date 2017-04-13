package ccr.cleanarchisample.list;

import com.nicolasmouchel.executordecorator.ImmutableExecutorDecorator;
import com.nicolasmouchel.executordecorator.MutableExecutorDecorator;

import java.util.concurrent.Executors;

import ccr.cleanarchisample.core.GitHubRepository;
import ccr.cleanarchisample.list.core.GitHubForkListInteractor;
import ccr.cleanarchisample.list.core.GitHubForkListInteractorImpl;
import ccr.cleanarchisample.list.presentation.GitHubForkListPresenterImpl;
import ccr.cleanarchisample.list.presentation.GitHubForkListView;
import ccr.cleanarchisample.utils.UiThreadExecutor;

public class GitHubForkListModule {

    private final GitHubForkListInteractor interactor;
    private final GitHubForkListView view;

    public GitHubForkListModule(GitHubRepository repository) {
        view = provideView();
        interactor = provideInteractor(repository);
    }

    @ImmutableExecutorDecorator
    private GitHubForkListInteractor provideInteractor(GitHubRepository repository) {
        return new GitHubForkListInteractorDecorator(
                Executors.newSingleThreadExecutor(),
                new GitHubForkListInteractorImpl(
                        new GitHubForkListPresenterImpl(view),
                        repository));
    }

    @MutableExecutorDecorator
    private GitHubForkListView provideView() {
        return new GitHubForkListViewDecorator(new UiThreadExecutor());
    }

    public GitHubForkListViewDecorator getViewDecorator() {
        return (GitHubForkListViewDecorator) view;
    }

    public GitHubForkListInteractor getInteractor() {
        return interactor;
    }

}
