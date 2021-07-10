package api.generic;
import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import api.retrofit.generic.Repos;
import com.sun.tools.jconsole.JConsoleContext;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.REPO_USERNAME;
import static api.retrofit.generic.Repos.*;
import static api.validators.ResponseValidator.assertCreated;
import static api.validators.ResponseValidator.assertOk;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Dante Ferreira Marinho
 */
public class GetRepoTests {

    // public static final String USERNAME = "dantinTests";
    private static String repoName;
    public static String repoDescription;
    private static Response<Repo> response;

    // Auxiliar method to create a repository
    @BeforeClass
    public static void setupClass() throws IOException {
        System.out.println("Before class");

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
    public static void afterClass() throws IOException {
        System.out.println("After class");
        response = deleteRepo(REPO_USERNAME, repoName);
    }

    @Test
    public void repoShouldNotBeNull() {
        Repo repo = response.body();
        assertThat("Repository should not be null", repo, notNullValue());
    }

    @Test
    public void repoNameShoulNotBeNull() {
        Repo repo = response.body();
        assertThat("Repository name should not be null", repo.getName(), notNullValue());
    }

    @Test
    public void repoNameShoulMatch() {
        Repo repo = response.body();
        assertThat("Repository name should matche.", repo.getName(), is(repoName));
    }

    @Test
    public void repoNameShoulStartsWith() {
        Repo repo = response.body();
        assertThat("Repository name should starts with.", repo.getName(), startsWith(repoName.substring(0, 3)));
    }

    @Test
    public void repoDescriptionShoulMatch() {
        Repo repo = response.body();
        assertThat("Repository description should matche.", repo.getDescription(), is(repoDescription));
    }
}
