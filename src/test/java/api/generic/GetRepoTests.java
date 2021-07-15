package api.generic;

import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.REPO_USERNAME;
import static api.retrofit.generic.Repos.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Dante Ferreira Marinho
 */
public class GetRepoTests {

    private String repoName;
    private String repoDescription;
    private Response<Repo> response;

    // Auxiliar method to create a repository
    @BeforeClass
    public void setupBeforeClass() throws IOException {

        repoName = "javascript-ninja-course-"  + Math.random();
        repoDescription = "JavaScript Ninja Course description";

        // --- Cenário :: todos os testes desta classe utilzarão este mesmo cenário
        RepoBody repoBody = RepoBody.builder()
                .name(repoName)
                .description(repoDescription)
                .auto_init(true)
                .build();
        createRepo(repoBody);

        // --- Ação :: todos testes desta classe utilizarão esta mesma ação
        response = getRepoByRepoName(REPO_USERNAME, repoName);
    }

    @AfterClass
    public void cleaningAfterClass() throws IOException {
        response = deleteRepo(REPO_USERNAME, repoName);
    }

    // --- Tests

    @Test(description = "The repo should not be null") // CT 01
    public void repoShouldNotBeNull() {
        Repo repo = response.body();
        assertThat("repository should not be null", repo, notNullValue());
    }

    @Test(description = "Repo name should not be null") // CT 02
    public void repoNameShoulNotBeNull() {
        Repo repo = response.body();
        assertThat("repository name should not be null", repo.getName(), notNullValue());
    }

    @Test(description = "Repo name should match") // CT 03
    public void repoNameShoulMatch() {
        Repo repo = response.body();
        assertThat("repository name should match.", repo.getName(), is(repoName));
    }

    @Test(description = "Repo name should starts with") // CT 04
    public void repoNameShoulStartsWith() {
        Repo repo = response.body();
        assertThat(String.format("repository name should starts with.", repoName.substring(0, 3)), repo.getName(), startsWith(repoName.substring(0, 3)));
    }

    @Test(description = "Repo description should match") // CT 05
    public void repoDescriptionShoulMatch() {
        Repo repo = response.body();
        assertThat("repository description should match.", repo.getDescription(), is(repoDescription));
    }
}
