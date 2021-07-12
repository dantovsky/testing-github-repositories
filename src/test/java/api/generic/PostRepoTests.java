package api.generic;

import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.REPO_USERNAME;
import static api.retrofit.generic.Repos.createRepo;
import static api.retrofit.generic.Repos.deleteRepo;
import static api.validators.ResponseValidator.assertNoContent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Dante Ferreira Marinho
 */
public class PostRepoTests {

    public static String repoName;
    public static String repoDescription;
    public Response<Repo> response;

    @BeforeMethod
    public void setupBeforeTest() throws IOException {

        repoName = "Java-Ninja-" + Math.random();
        repoDescription = "Description Repository Java Ninja.";

        RepoBody repoBody = RepoBody.builder()
                .name(repoName) // to ensure that each test has its own POST call.
                .description(repoDescription)
                .has_wiki(true)
                .auto_init(true)
                .build();

        response = createRepo(repoBody);
    }

    @AfterMethod
    public void cleaningAfterEachTest() throws IOException {

        response = deleteRepo(REPO_USERNAME,repoName);
        assertNoContent(response);
    }

    // --- Tests

    @Test(description = "Shoul return object not null")
    public void shouldReturnObjectNotNull() throws IOException {

        Repo repo = response.body();
        assertThat("response should not be null", repo, notNullValue());
    }

    @Test(description = "Name should starts with Java")
    public void nameRepoShoulNotBeNull() throws IOException {

        Repo repo = response.body();
        assertThat("name should not be null", repo.getName(), notNullValue());
    }

    @Test(description = "Name should starts with 'Java'")
    public void nameRepoShoulStartsWithJava() throws IOException {

        Repo repo = response.body();
        assertThat("name should starts with Java", repo.getName(), startsWith("Java"));
    }

    @Test(description = "Description should match")
    public void descriptionRepoShoudMatch() throws IOException {

        Repo repo = response.body();
        assertThat("description should be Description Repository Java Ninja", repo.getDescription(), is(repoDescription));
    }

    @Test(description = "Description should starts with 'Description'")
    public void descriptionRepoShoudStartsWithDescription() throws IOException {

        Repo repo = response.body();
        assertThat("description should starts with 'Description'", repo.getDescription(), startsWith("Description"));
    }

//    @Test(description = "Repo visibility is 'public' (not private)")
//    public void repoHasPublicVisibility() throws IOException {
//
//        Repo repo = response.body();
//        assertThat("repo has 'public' visibility", repo.getVisibility(), is("public"));
//    }

    @Test(description = "Repo has wiki activated")
    public void repoHasWiki() throws IOException {

        Repo repo = response.body();
        assertThat("repo has wiki", repo.getHas_wiki(), is(true));
    }

    @Test(description = "Trying to create a repository with same name shoul return status 422")
    public void creatingRepoWithSameName() throws IOException {

        RepoBody repoBody = RepoBody.builder()
                .name(repoName) // Using the same name to create a repo
                .description("New Java Repository")
                .auto_init(false)
                .build();

        Response<Repo> responseSameRepo = createRepo(repoBody);

        assertThat("existing repo :: should return status code 422", responseSameRepo.code(), is(422));
    }
}
