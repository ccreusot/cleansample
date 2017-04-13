package ccr.cleanarchisample.core;

import java.util.List;

import ccr.cleanarchisample.model.GitHubFork;

public interface GitHubRepository {
    List<GitHubFork> getForkList() throws GitHubRepositoryException;

    GitHubFork getForkDetail(String id) throws GitHubRepositoryException;

    class GitHubRepositoryException extends Exception{
    }
}
