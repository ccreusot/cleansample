package ccr.cleanarchisample.detail.core;

import ccr.cleanarchisample.core.GitHubRepository;
import ccr.cleanarchisample.detail.presentation.GitHubForkDetailPresenter;
import ccr.cleanarchisample.model.GitHubFork;

public class GitHubForkDetailInteractorImpl implements GitHubForkDetailInteractor {
    private final GitHubForkDetailPresenter presenter;
    private final GitHubRepository repository;

    public GitHubForkDetailInteractorImpl(GitHubForkDetailPresenter presenter, GitHubRepository repository) {
        this.presenter = presenter;
        this.repository = repository;
    }

    @Override
    public void fetchGitHubForkDetail(String id) {
        try {
            GitHubFork forkDetail = repository.getForkDetail(id);
            if (forkDetail != null) {
                presenter.presentDetail(forkDetail);
            } else {
                presenter.presentNothing();
            }
        } catch (GitHubRepository.GitHubRepositoryException e) {
            presenter.presentError();
        }
    }
}
