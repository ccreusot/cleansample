package ccr.cleanarchisample.detail.presentation;

import ccr.cleanarchisample.model.GitHubFork;

public interface GitHubForkDetailPresenter {
    void presentNothing();

    void presentError();

    void presentDetail(GitHubFork gitHubFork);
}
