package ccr.cleanarchisample.core;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import ccr.cleanarchisample.model.GitHubFork;
import ccr.cleanarchisample.model.JsonGitHubFork;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

public class GitHubRepositoryImpl implements GitHubRepository {

    private final GitHubForkService gitHubForkService;
    private List<GitHubFork> gitHubForks;
    private static GitHubRepositoryImpl instance = null;

    public static GitHubRepository getInstance() {
        if (instance == null) {
            instance = new GitHubRepositoryImpl();
        }
        return instance;
    }

    private GitHubRepositoryImpl() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build();
        gitHubForkService = retrofit.create(GitHubForkService.class);
        gitHubForks = new ArrayList<>();
    }

    @Override
    public List<GitHubFork> getForkList() throws GitHubRepositoryException {
        try {
            if (gitHubForks.isEmpty()) {
                Response<List<JsonGitHubFork>> execute = gitHubForkService.getForkListFromDefinitelyTyped().execute();
                List<JsonGitHubFork> body = execute.body();
                gitHubForks.addAll(body);
            }
            return gitHubForks;
        } catch (Exception e) {
            throw new GitHubRepositoryException();
        }
    }

    @Override
    public GitHubFork getForkDetail(String id) throws GitHubRepositoryException {
        for (GitHubFork fork : gitHubForks) {
            if (fork.getId().equals(id)) {
                return fork;
            }
        }
        return null;
    }

    interface GitHubForkService {
        @GET("/repos/DefinitelyTyped/DefinitelyTyped/forks")
        Call<List<JsonGitHubFork>> getForkListFromDefinitelyTyped();
    }
}
