package ccr.cleanarchisample.core;

import java.util.List;

import ccr.cleanarchisample.model.GitHubFork;

public interface GitHubRepository {
    List<GitHubFork> getForkList() throws GitHubRepositoryException;

    class GitHubRepositoryException extends Exception{
    }
}
