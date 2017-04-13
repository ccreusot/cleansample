package ccr.cleanarchisample.detail.presentation;

import ccr.cleanarchisample.detail.viewmodel.GitHubForkDetailViewModel;

public interface GitHubForkDetailView {
    void displayNothing();

    void displayError();

    void displayDetail(GitHubForkDetailViewModel capture);
}
