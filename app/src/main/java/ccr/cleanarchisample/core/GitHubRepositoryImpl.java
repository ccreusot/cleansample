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

    public GitHubRepositoryImpl() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build();
        gitHubForkService = retrofit.create(GitHubForkService.class);
    }

    @Override
    public List<GitHubFork> getForkList() throws GitHubRepositoryException {
        try {
            Response<List<JsonGitHubFork>> execute = gitHubForkService.getForkListFromDefinitelyTyped().execute();
            List<GitHubFork> gitHubForks = new ArrayList<>();
            List<JsonGitHubFork> body = execute.body();
            gitHubForks.addAll(body);
            return gitHubForks;
        } catch (Exception e) {
            throw new GitHubRepositoryException();
        }
    }

    //https://api.github.com/repos/DefinitelyTyped/DefinitelyTyped/forks
    interface GitHubForkService {
        @GET("/repos/DefinitelyTyped/DefinitelyTyped/forks")
        Call<List<JsonGitHubFork>> getForkListFromDefinitelyTyped();
    }
}
