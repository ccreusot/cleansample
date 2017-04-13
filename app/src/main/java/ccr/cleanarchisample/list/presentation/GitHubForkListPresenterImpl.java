package ccr.cleanarchisample.list.presentation;

import java.util.ArrayList;
import java.util.List;

import ccr.cleanarchisample.model.GitHubFork;
import ccr.cleanarchisample.list.viewmodel.GitHubForkItemViewModel;

public class GitHubForkListPresenterImpl implements GitHubForkListPresenter {
    private final GitHubForkListView view;

    public GitHubForkListPresenterImpl(GitHubForkListView view) {
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
    public void presentList(List<GitHubFork> gitHubForks) {
        view.displayList(convertListToViewModel(gitHubForks));
    }

    private List<GitHubForkItemViewModel> convertListToViewModel(List<GitHubFork> gitHubForks) {
        List<GitHubForkItemViewModel> itemViewModelList = new ArrayList<>(gitHubForks.size());
        for (GitHubFork gitHubFork : gitHubForks) {
            itemViewModelList.add(convertGitHubForkToViewModel(gitHubFork));
        }
        return itemViewModelList;
    }

    private GitHubForkItemViewModel convertGitHubForkToViewModel(GitHubFork gitHubFork) {
        return new GitHubForkItemViewModel(gitHubFork.getId(), gitHubFork.getTitle(), gitHubFork.getUrlImage());
    }
}
