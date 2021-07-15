package api.generic;

import api.mappings.generic.ErrorResponse;
import api.mappings.generic.Repo;
import api.mappings.generic.RepoBody;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

import static api.data.Common.REPO_USERNAME;
import static api.retrofit.generic.Errors.getErrorsResponse;
import static api.retrofit.generic.Repos.*;
import static api.validators.ErrorResponseValidator.assertErrorResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Dante Ferreira Marinho
 */
public class PatchRepoTests {

    private String repoName;
    private String repoDescription;
    private Response<Repo> response;

    @BeforeClass
    public void setupBeforeClass() throws IOException {

        repoName = "Temporary-Repository-Patch";
        repoDescription = "Repo using for updates.";

        // --- Cenário :: POST - todos os testes desta classe utilzarão este mesmo cenário
        RepoBody repoBody = RepoBody.builder()
                .name(repoName)
                .description(repoDescription)
                .has_wiki(true)
                .auto_init(true)
                .delete_branch_on_merge(true)
                .build();
        createRepo(repoBody);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
        deleteRepo(REPO_USERNAME, repoName);
    }

    // --- Tests

    @Test(description = "Should get status code 200") // CT 14
    public void shouldGetStatusCode200() {

        assertThat("the status code should be 200", response.code(), is(200));
    }

    @Test(description = "Should be not null value") // CT 15
    public void shouldNotBeNullValue() {

        Repo repo = response.body();
        assertThat("repo should not be null", repo, notNullValue());
    }

    @Test(description = "Should match the new name") // CT 16
    public void shouldMatchNewName() {

        Repo repo = response.body();
        assertThat(String.format("the new name should be [%s]", repoName), repo.getName(), is(repoName));
    }

    @Test(description = "Should match the new description") // CT 17
    public void shouldMatchNewDescription() {

        Repo repo = response.body();
        assertThat(String.format("the new description should be [%s]", repoDescription + " (updated)"), repo.getDescription(), is(repoDescription + " (updated)"));
    }

    @Test(description = "Should not have wiki") // CT 18
    public void shouldNotHaveWiki() {

        Repo repo = response.body();
        assertThat("has_wiki was setted false", repo.getHas_wiki(), is(false));
    }

    @Test(description = "Should have a homepage") // CT 19
    public void shouldHaveHomepage() {

        Repo repo = response.body();
        assertThat("homepage should be https://www.imediacto.com", repo.getHomepage(), is("https://www.imediacto.com"));
    }

    @Test(description = "Should have zero open issues") // CT 20
    public void shouldHaveZeroOpenIssues() {

        Repo repo = response.body();
        assertThat("open_issues should be 0", repo.getOpen_issues(), is(0));
    }

    @Test(description = "Param delete_branch_on_merge should be true") // CT 21
    public void shouldDeleteBranchOnMergeBeTrue() {

        Repo repo = response.body();
        assertThat("delete_branch_on_merge should be true", repo.getDelete_branch_on_merge(), is(true));
    }

    @Test(description = "Param html_url should match") // CT 22
    public void shouldHtmlUrlMatch() {

        String htmlUrl = "https://github.com/" + REPO_USERNAME + "/" + repoName;
        Repo repo = response.body();

        assertThat(String.format("html_url should be [%s]", htmlUrl), repo.getHtml_url(), is(htmlUrl));
    }

    @Test(description = "Param git_url should match") // CT 23
    public void shouldGitUrlMatch() {

        String gitUrl = "git://github.com/" + REPO_USERNAME + "/" + repoName + ".git";
        Repo repo = response.body();

        assertThat(String.format("git_url should be [%s]", gitUrl), repo.getGit_url(), is(gitUrl));
    }

    @Test(description = "Param ssh_url should match") // CT 24
    public void shouldSshUrlMatch() {

        String sshUrl = "git@github.com:" + REPO_USERNAME + "/" + repoName + ".git";
        Repo repo = response.body();

        assertThat(String.format("ssh_url should be [%s]", sshUrl), repo.getSsh_url(), is(sshUrl));
    }

    @Test(description = "Param clone_url should match") // CT 25
    public void shouldCloneUrlMatch() {

        String cloneUrl = "https://github.com/" + REPO_USERNAME + "/" + repoName + ".git";
        Repo repo = response.body();

        assertThat(String.format("clone_url should be [%s]", cloneUrl), repo.getClone_url(), is(cloneUrl));
    }

    @Test(description = "Param svn_url should match") // CT 26
    public void shouldSvnUrlMatch() {

        String svnUrl = "https://github.com/" + REPO_USERNAME + "/" + repoName;
        Repo repo = response.body();

        assertThat(String.format("svn_url should be [%s]", svnUrl), repo.getSvn_url(), is(svnUrl));
    }

    @Test(description = "The repo should not be archived") // CT 27
    public void shouldNotBeArchived() {

        Repo repo = response.body();
        assertThat(String.format("should be archived assigned as [%b]", false), repo.getArchived(), is(false));
    }

    @Test(description = "Trying to update a non existent repository") // CT 28
    public void updateRepositoryNonExistent() throws IOException {

        RepoBody repoBodyPatch = RepoBody.builder()
                .name("The Other New Name")
                .build();

        response = updateRepo(REPO_USERNAME, "The Fake Repo", repoBodyPatch);
        assertThat("the repository doesn't exists", response.code(), is(404));

        ErrorResponse error = getErrorsResponse(response);
        assertErrorResponse(error, "Not Found", "https://docs.github.com/rest/reference/repos#update-a-repository");
    }

    @Test(description = "Trying to update a non existent repository") // CT 29
    public void updateRepositoryNonExistentMessage() throws IOException {

        RepoBody repoBodyPatch = RepoBody.builder()
                .name("The Other New Name")
                .build();

        response = updateRepo(REPO_USERNAME, "The Fake Repo", repoBodyPatch);
        assertThat("the message is 'Not Found'", response.message(), is(oneOf("Not Found", "")));

        ErrorResponse error = getErrorsResponse(response);
        assertErrorResponse(error, "Not Found", "https://docs.github.com/rest/reference/repos#update-a-repository");
    }
}
















































