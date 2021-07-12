package api.retrofit.generic;

import api.calls.RepoCalls;
import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import api.retrofit.RetrofitBuilder;
import lombok.NoArgsConstructor;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.ACCEPT_HEADER;
import static api.data.Common.TOKEN;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class Repos {

    private static final RepoCalls repoCalls = new RetrofitBuilder().getRetrofit().create(RepoCalls.class);

    public static Response<Repo> getRepoByRepoName(String username, String name) throws IOException {
        return repoCalls.getRepoByRepoName(username, name).execute();
    }

    public static Response<Repo> createRepo(RepoBody repoBody) throws IOException {
        return repoCalls.createRepo(TOKEN, ACCEPT_HEADER, repoBody).execute();
    }

    public static Response<Repo> deleteRepo(String username, String repoName) throws IOException {
        return repoCalls.deleteRepo(TOKEN, ACCEPT_HEADER, username, repoName).execute();
    }

    public static Response<Repo> updateRepo(String username, String repoName, RepoBody repoBody) throws IOException {
        return repoCalls.updateRepo(TOKEN, ACCEPT_HEADER, username, repoName, repoBody).execute();
    }
}
