package ccr.cleanarchisample.detail;

import com.nicolasmouchel.executordecorator.ImmutableExecutorDecorator;
import com.nicolasmouchel.executordecorator.MutableExecutorDecorator;

import java.util.concurrent.Executors;

import ccr.cleanarchisample.core.GitHubRepository;
import ccr.cleanarchisample.detail.core.GitHubForkDetailInteractor;
import ccr.cleanarchisample.detail.core.GitHubForkDetailInteractorImpl;
import ccr.cleanarchisample.detail.presentation.GitHubForkDetailPresenterImpl;
import ccr.cleanarchisample.detail.presentation.GitHubForkDetailView;
import ccr.cleanarchisample.utils.UiThreadExecutor;

public class GitHubForkDetailModule {

    private final GitHubForkDetailView view;
    private final GitHubForkDetailInteractor interactor;

    public GitHubForkDetailModule(GitHubRepository repository) {
        view = provideView();
        interactor = provideInteractor(repository);
    }

    @ImmutableExecutorDecorator
    private GitHubForkDetailInteractor provideInteractor(GitHubRepository repository) {
        return new GitHubForkDetailInteractorDecorator(
            Executors.newSingleThreadExecutor(),
            new GitHubForkDetailInteractorImpl(
                new GitHubForkDetailPresenterImpl(view),
                repository));
    }

    @MutableExecutorDecorator
    private GitHubForkDetailView provideView() {
        return new GitHubForkDetailViewDecorator(new UiThreadExecutor());
    }

    public GitHubForkDetailViewDecorator getView() {
        return (GitHubForkDetailViewDecorator) view;
    }

    public GitHubForkDetailInteractor getInteractor() {
        return interactor;
    }
}
