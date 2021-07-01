package api.calls;

import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface RepoCalls {

    // String NAME = "name";
    String REPO = "repo";
    String USERNAME = "username";
    String REPOS_USER_REPO_NAME = "repos/{username}/{repo}"; // GET / DELETE
    String USER_REPOS = "/user/repos"; // POST
    String ACCEPT = "accept";
    String AUTHORIZATION = "Authorization";

    @GET(REPOS_USER_REPO_NAME)
    Call<Repo> getRepoByRepoName(@Path(USERNAME) String username, @Path(REPO) String name);

    @POST(USER_REPOS)
    Call<Repo> createRepo(@Header(AUTHORIZATION) String token, @Header(ACCEPT) String acceptHeader, @Body RepoBody repoBody);

    @DELETE(REPOS_USER_REPO_NAME)
    Call<Repo> deleteRepo(@Header(AUTHORIZATION) String token, @Header(ACCEPT) String acceptHeader, @Path(USERNAME) String username, @Path(REPO) String repoName);
}
