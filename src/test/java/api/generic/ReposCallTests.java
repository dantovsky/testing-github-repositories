package api.generic;

import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import api.retrofit.generic.Repos;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.retrofit.generic.Repos.*;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Dante Ferreira Marinho
 */
public class ReposCallTests {

    public static final String USERNAME = "dantinTests";
    public static final String REPO_NAME = "html-course";

    @Test(description = "Get a repository by name")
    public void getARepositoryByName() throws IOException {

        // Cenário
        Response<Repo> response = getRepoByRepoName(USERNAME, REPO_NAME);
        assertOk(response);

        // Ação
        Repo repo = response.body();

        // Validação
        assertThat("Repository should not be null", repo, notNullValue());
        assertThat("Repository name should not be null", repo.getName(), notNullValue());
        assertThat("Repository name should matche.", repo.getName(), is(REPO_NAME));
    }

    @Test(description = "Create a repository with authentication")
    public void createRepositoryTest() throws IOException {

        RepoBody repoBody = RepoBody.builder()
                .name("The New Repo " + Math.random())
                .description("Description for may New Repo")
                .auto_init(true)
                .build();

        Response<Repo> response = createRepo(repoBody);
        assertCreated(response);

        Repo repo = response.body();

        assertThat("response should not be null", repo, notNullValue());
        assertThat("name should be the same", repo.getName(), startsWith(repoBody.getName().split(" ")[0]));
        assertThat("description should be ", repo.getDescription(), is(repoBody.getDescription()));
        assertThat("the repository visibility is public (not private)", repo.getPrivateProp(), is(false));
        assertThat("the repository has wiki", repo.getHas_wiki(), is(true));
    }

    @Test(description = "Trying to create a repository with same name")
    public void createRepositoryWithSameNameTest() throws IOException {

        RepoBody repoBody = RepoBody.builder()
                .name("html-css-course-" + Math.random())
                .description("HTML / CSS Course description")
                .auto_init(true)
                .build();

        createRepo(repoBody);
        Response<Repo> response2 = createRepo(repoBody);

        assertThat("the repository already exists", response2.code(), is(422));
    }

    @Test(description = "Delete a repository")
    public void deleteRepositoryTest() throws IOException, InterruptedException {

        // Temporary repository to be deleted
        RepoBody repoBody = RepoBody.builder()
                .name("Temporary-Repository-Delete")
                .description("Temporary repository to be deleted")
                .auto_init(true)
                .build();

        Response<Repo> responseTempRepo = createRepo(repoBody);
        assertCreated(responseTempRepo);

        // Get the name for temp repo
        String repoName = responseTempRepo.body().getName();
        Response<Repo> response = deleteRepo(USERNAME, repoName);

        assertThat("the repository should have status code 204 (deleted)", response.code(), is(204));
    }

    @Test(description = "Trying to delete a non existent repository")
    public void deleteRepositoryNonExistentTest() throws IOException {

        Response<Repo> response = deleteRepo(USERNAME,"The Fake Repo");

        assertThat("the repository doesn't exists", response.code(), is(404));
    }
}
