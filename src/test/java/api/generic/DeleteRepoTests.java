package api.generic;

import api.mappings.generic.ErrorResponse;
import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.REPO_USERNAME;
import static api.retrofit.generic.Errors.getErrorsResponse;
import static api.retrofit.generic.Repos.createRepo;
import static api.retrofit.generic.Repos.deleteRepo;
import static api.validators.ErrorResponseValidator.assertErrorResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author Dante Ferreira Marinho
 */
public class DeleteRepoTests {

    private String repoName;
    public String repoDescription;

    @BeforeMethod
    public void setupBeforeMethod() throws IOException {
        System.out.println("Before class");

        repoName = "Temporary-Repository-Delete" + Math.random();
        repoDescription = "Temporary repository to be deleted";

        // --- Cenário :: todos os testes desta classe utilzarão este mesmo cenário
        RepoBody repoBody = RepoBody.builder()
                .name(repoName)
                .description(repoDescription)
                .auto_init(true)
                .build();

        createRepo(repoBody);
    }

    // --- @AfterMethod ::
    // Não será utilizado o @AfterMethod, devido à característica deste verbo HTTP,
    // onde já irá realizar o delete a cada teste realizado.

    // --- Tests

    @Test(description = "Delete a repository")
    public void deleteRepositoryTest() throws IOException {

        Response<Repo> response = deleteRepo(REPO_USERNAME, repoName);
        assertThat("the repository should have status code 204 (deleted)", response.code(), is(204));
    }

    @Test(description = "The response hasn't a body")
    public void deletedRepoHasNotBody() throws IOException {

        Response<Repo> response = deleteRepo(REPO_USERNAME, repoName);
        assertThat("the deleted repository response shouldn't have a body", response.body(), nullValue());
    }

    @Test(description = "Deleting repository operation is successful")
    public void deleteRepoIsSuccessful() throws IOException {

        Response<Repo> response = deleteRepo(REPO_USERNAME, repoName);
        assertThat("the repository should have status code 204 (deleted)", response.isSuccessful(), is(true));
    }

    @Test(description = "Trying to delete a non existent repository")
    public void deleteRepositoryNonExistent() throws IOException {

        Response<Repo> response = deleteRepo(REPO_USERNAME,"The Fake Repo");
        assertThat("the repository doesn't exists", response.code(), is(404));

        ErrorResponse error = getErrorsResponse(response);
        assertErrorResponse(error, "Not Found", "https://docs.github.com/rest/reference/repos#delete-a-repository");

        // --- Limpando o repositório atual criado pelo @BeforeMethod
        deleteRepo(REPO_USERNAME, repoName);
    }
}
