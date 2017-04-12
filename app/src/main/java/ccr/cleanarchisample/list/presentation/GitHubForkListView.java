package ccr.cleanarchisample.list.presentation;

import java.util.List;

import ccr.cleanarchisample.list.viewmodel.GitHubForkItemViewModel;

public interface GitHubForkListView {
    void displayNothing();

    void displayError();

    void displayList(List<GitHubForkItemViewModel> viewModelList);
}
