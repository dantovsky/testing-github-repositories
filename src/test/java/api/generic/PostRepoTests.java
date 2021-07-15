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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Dante Ferreira Marinho
 */
public class PostRepoTests {

    private String repoName;
    private String repoDescription;
    private Response<Repo> response;

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

        deleteRepo(REPO_USERNAME,repoName);
    }

    // --- Tests

    @Test(description = "Shoul return object not null") // CT 06
    public void shouldReturnObjectNotNull() {

        Repo repo = response.body();
        assertThat("response should not be null", repo, notNullValue());
    }

    @Test(description = "Name should starts with Java") // CT 07
    public void nameRepoShoulNotBeNull() {

        Repo repo = response.body();
        assertThat("name should not be null", repo.getName(), notNullValue());
    }

    @Test(description = "Name should starts with 'Java'") // CT 08
    public void nameRepoShoulStartsWithJava() {

        Repo repo = response.body();
        assertThat("name should starts with Java", repo.getName(), startsWith("Java"));
    }

    @Test(description = "Description should match") // CT 09
    public void descriptionRepoShoudMatch() {

        Repo repo = response.body();
        assertThat("description should be Description Repository Java Ninja", repo.getDescription(), is(repoDescription));
    }

    @Test(description = "Description should starts with 'Description'") // CT 10
    public void descriptionRepoShoudStartsWithDescription() {

        Repo repo = response.body();
        assertThat("description should starts with 'Description'", repo.getDescription(), startsWith("Description"));
    }

    @Test(description = "Repo visibility is 'public' (not private)") // CT 11
    public void repoHasPublicVisibility() {

        Repo repo = response.body();
        assertThat("repo has 'public' visibility", repo.getVisibility(), is("public"));
    }

    @Test(description = "Repo has wiki activated") // CT 12
    public void repoHasWiki() {

        Repo repo = response.body();
        assertThat("repo has wiki", repo.getHas_wiki(), is(true));
    }

    @Test(description = "Trying to create a repository with same name shoul return status 422") // CT 13
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
