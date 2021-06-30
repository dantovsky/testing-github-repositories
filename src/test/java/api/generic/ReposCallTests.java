package api.generic;

import api.mappings.generic.Repo;
import api.mappings.generic.ErrorResponse;
import okhttp3.ResponseBody;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.retrofit.generic.Errors.getErrorsResponse;
import static api.retrofit.generic.Repos.*;
import static api.validators.ErrorResponseValidator.assertErrorResponse;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static api.validators.ResponseValidator.assertUnauthorized;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ReposCallTests {

    @Test(description = "Get a repository by name")
    public void getARepositoryByName() throws IOException {
        Response<Repo> response = getRepoByRepoName("empty-repo");
        assertOk(response);

        Repo repo = response.body();

        assertThat("Repository should not be null", repo, notNullValue());
        assertThat("Repository name should not be null", repo.getName(), notNullValue());
        assertThat("Repository name should be empty-repo", repo.getName(), is("empty-repo"));
    }

//    @Test(description = "get something from a generic Call")
//    public void getSomethingFromGenericCallTest() throws IOException {
//        Response<ResponseBody> response = getGenericCall();
//        assertOk(response);
//    }
//
//    @Test(description = "create something using a call without authentication")
//    public void createSomethingWithoutAuthentication() throws IOException {
//        Response<ResponseBody> response = createGenericCallNoAuth(new Repo());
//        assertUnauthorized(response);
//        ErrorResponse error = getErrorsResponse(response);
//        assertErrorResponse(error, "Requires authentication", "https://docs.github.com/rest/reference/<example>");
//    }
//
//    @Test(description = "create something using a call without authentication")
//    public void createSomethingTest() throws IOException {
//        Response<ResponseBody> response = createGenericCall(new Repo());
//        assertCreated(response);
//    }
}
