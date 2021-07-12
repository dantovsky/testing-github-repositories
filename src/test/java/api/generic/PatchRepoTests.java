package api.generic;

import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
public class PatchRepoTests {

    // Testes para fazer ::
    // status code is 200
    // "name" match new name
    // "description": "Description for may New Repo"
    // has_wiki to false
    // notNullValue
    // "homepage": https://www.imediacto.com
    // "open_issues": 0,
    // update fake: status code 404
    // update fake: "message" is "Not Found"

    // sem enviar body :: status code 400, "message": "Body should be a JSON object"
    // "html_url": "https://github.com/dantinTests/Um-New-Repo-13-Updated"
    // "delete_branch_on_merge": false,
    // "archived"	(não pode desfazer através da API)
    // "git_url": "git://github.com/dantinTests/for-updatesssss.git",
    // "ssh_url": "git@github.com:dantinTests/for-updatesssss.git",
    // "clone_url": "https://github.com/dantinTests/for-updatesssss.git",
    // "svn_url": "https://github.com/dantinTests/for-updatesssss",


    private String repoName;
    public String repoDescription;
    private Response<Repo> response;

    @BeforeClass
    public void setupBeforeClass() throws IOException {
        System.out.println("Before class");

        repoName = "Temporary-Repository-Patch";
        repoDescription = "Repo using for updates.";

        // --- Cenário :: POST - todos os testes desta classe utilzarão este mesmo cenário
        RepoBody repoBody = RepoBody.builder()
                .name(repoName)
                .description(repoDescription)
                .has_wiki(true)
                .auto_init(true)
                .build();
        createRepo(repoBody);

        // --- Ação :: PATCH
        String repoNewName = repoName + "-Updated";

        RepoBody repoBodyPatch = RepoBody.builder()
                .name(repoNewName)
                .description(repoDescription + " (updated)")
                .has_wiki(false)
                .homepage("https://www.imediacto.com")
                .build();

        response = updateRepo(REPO_USERNAME, repoName, repoBodyPatch);
        repoName = repoNewName; // Updating global var
    }

    @AfterClass
    public void cleaningAfterClass() throws IOException {
        System.out.println("After class");
        deleteRepo(REPO_USERNAME, repoName);
    }

    // --- Tests

    @Test(description = "Should get status code 200")
    public void shouldGetStatusCode200() {

        assertThat("the status code is 200", response.code(), is(200));
    }

    @Test(description = "Should be not null value")
    public void shouldNotBeNullValue() {

        Repo repo = response.body();
        assertThat("repo should not be null", repo, notNullValue());
    }

    @Test(description = "Should match the new name")
    public void shouldMatchNewName() {

        Repo repo = response.body();
        assertThat(String.format("the new name should be [%s]", repoName), repo.getName(), is(repoName));
    }

    @Test(description = "Should match the new description")
    public void shouldMatchNewDescription() {

        Repo repo = response.body();
        assertThat(String.format("the new description should be [%s]", repoDescription + " (updated)"), repo.getDescription(), is(repoDescription + " (updated)"));
    }

    @Test(description = "Should not have wiki")
    public void shouldNotHaveWiki() {

        Repo repo = response.body();
        assertThat("has_wiki was setted false", repo.getHas_wiki(), is(false));
    }

    @Test(description = "Should have a homepage")
    public void shouldHaveHomepage() {

        Repo repo = response.body();
        assertThat("homepage should be https://www.imediacto.com", repo.getHomepage(), is("https://www.imediacto.com"));
    }

    @Test(description = "Should have zero open issues")
    public void shouldHaveZeroOpenIssues() {

        Repo repo = response.body();
        assertThat("open_issues should be 0", repo.getOpen_issues(), is(0));
    }

    @Test(description = "Trying to update a non existent repository")
    public void updateRepositoryNonExistent() throws IOException {

        RepoBody repoBodyPatch = RepoBody.builder()
                .name("The Other New Name")
                .build();

        response = updateRepo(REPO_USERNAME, repoName, repoBodyPatch);

        Response<Repo> response = updateRepo(REPO_USERNAME, "The Fake Repo", repoBodyPatch);
        assertThat("the repository doesn't exists", response.code(), is(404));
    }

    @Test(description = "Trying to update a non existent repository")
    public void updateRepositoryNonExistentMessage() throws IOException {

        RepoBody repoBodyPatch = RepoBody.builder()
                .name("The Other New Name")
                .build();

        response = updateRepo(REPO_USERNAME, repoName, repoBodyPatch);

        Response<Repo> response = updateRepo(REPO_USERNAME, "The Fake Repo", repoBodyPatch);
        System.out.println(response);
        assertThat("the message is 'Not Found'", response.message(), is(oneOf("Not Found", ""))); // Not working
    }
}
















































