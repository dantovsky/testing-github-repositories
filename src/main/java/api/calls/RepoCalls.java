package api.calls;

import api.mappings.generic.Repo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface RepoCalls {

    String NAME = "name";
    String REPOS_DANTINTESTS_REPO_NAME = "repos/dantinTests/{name}"; // empty-repo
    String ACCEPT = "accept";
    String AUTHORIZATION = "Authorization";

    @GET(REPOS_DANTINTESTS_REPO_NAME)
    Call<Repo> getRepoByRepoName(@Path(NAME) String name);

//    @POST(PATH)
//    Call<ResponseBody> createGenericCall(@Header(AUTHORIZATION) String token, @Header(ACCEPT) String acceptHeader, @Body Repo repo);
}
