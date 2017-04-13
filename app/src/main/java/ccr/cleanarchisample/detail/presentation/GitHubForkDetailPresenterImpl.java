package ccr.cleanarchisample.detail.presentation;

import ccr.cleanarchisample.detail.viewmodel.GitHubForkDetailViewModel;
import ccr.cleanarchisample.model.GitHubFork;

public class GitHubForkDetailPresenterImpl implements GitHubForkDetailPresenter {

    private final GitHubForkDetailView view;

    public GitHubForkDetailPresenterImpl(GitHubForkDetailView view) {
        this.view = view;
    }

    @Override
    public void presentNothing() {
        view.displayNothing();
    }

    @Override
    public void presentError() {
        view.displayError();
    }

    @Override
    public void presentDetail(GitHubFork gitHubFork) {
        view.displayDetail(convertGitHubForkIntoViewModel(gitHubFork));
    }

    private GitHubForkDetailViewModel convertGitHubForkIntoViewModel(GitHubFork gitHubFork) {
        return new GitHubForkDetailViewModel(gitHubFork.getTitle(), gitHubFork.getUrlImage(), gitHubFork.getDescription());
    }
}
