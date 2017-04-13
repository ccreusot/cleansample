package ccr.cleanarchisample.list.core;

import java.util.List;

import ccr.cleanarchisample.core.GitHubRepository;
import ccr.cleanarchisample.model.GitHubFork;
import ccr.cleanarchisample.list.presentation.GitHubForkListPresenter;

public class GitHubForkListInteractorImpl implements GitHubForkListInteractor {

    private final GitHubForkListPresenter presenter;
    private final GitHubRepository repository;

    public GitHubForkListInteractorImpl(GitHubForkListPresenter presenter, GitHubRepository repository) {
        this.presenter = presenter;
        this.repository = repository;
    }

    @Override
    public void fetchForkList() {
        try {
            List<GitHubFork> gitHubForks = repository.getForkList();
            if (gitHubForks != null && !gitHubForks.isEmpty()) {
                presenter.presentList(gitHubForks);
            } else {
                presenter.presentNothing();
            }
        } catch (GitHubRepository.GitHubRepositoryException e) {
            presenter.presentError();
        }
    }
}
