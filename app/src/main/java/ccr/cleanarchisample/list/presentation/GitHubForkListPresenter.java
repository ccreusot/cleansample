package ccr.cleanarchisample.list.presentation;

import java.util.List;

import ccr.cleanarchisample.model.GitHubFork;

public interface GitHubForkListPresenter {
    void presentNothing();

    void presentList(List<GitHubFork> gitHubForks);

    void presentError();
}
