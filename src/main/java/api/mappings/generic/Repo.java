package api.mappings.generic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties
public class Repo {

    @JsonProperty("stargazers_count")
    private Integer stargazers_count;

    @JsonProperty("is_template")
    private Boolean is_template;

    @JsonProperty("pushed_at")
    private String pushed_at;

    @JsonProperty("subscription_url")
    private String subscription_url;

    @JsonProperty("language")
    private String language;

    @JsonProperty("branches_url")
    private String branches_url;

    @JsonProperty("issue_comment_url")
    private String issue_comment_url;

    @JsonProperty("allow_rebase_merge")
    private Boolean allow_rebase_merge;

    @JsonProperty("labels_url")
    private String labels_url;

    @JsonProperty("subscribers_url")
    private String subscribers_url;

    @JsonProperty("permissions")
    private Permissions permissions;

    @JsonProperty("temp_clone_token")
    private String temp_clone_token;

    @JsonProperty("releases_url")
    private String releases_url;

    @JsonProperty("svn_url")
    private String svn_url;

    @JsonProperty("subscribers_count")
    private Integer subscribers_count;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("forks")
    private Integer forks;

    @JsonProperty("archive_url")
    private String archive_url;

    @JsonProperty("allow_merge_commit")
    private Boolean allow_merge_commit;

    @JsonProperty("git_refs_url")
    private String git_refs_url;

    @JsonProperty("forks_url")
    private String forks_url;

    @JsonProperty("visibility")
    private String visibility;

    @JsonProperty("statuses_url")
    private String statuses_url;

    @JsonProperty("network_count")
    private Integer network_count;

    @JsonProperty("ssh_url")
    private String ssh_url;

    @JsonProperty("license")
    private License license;

    @JsonProperty("full_name")
    private String full_name;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("template_repository")
    private String template_repository;

    @JsonProperty("languages_url")
    private String languages_url;

    @JsonProperty("html_url")
    private String html_url;

    @JsonProperty("collaborators_url")
    private String collaborators_url;

    @JsonProperty("clone_url")
    private String clone_url;

    @JsonProperty("name")
    private String name;

    @JsonProperty("pulls_url")
    private String pulls_url;

    @JsonProperty("default_branch")
    private String default_branch;

    @JsonProperty("hooks_url")
    private String hooks_url;

    @JsonProperty("trees_url")
    private String trees_url;

    @JsonProperty("tags_url")
    private String tags_url;

    @JsonProperty("private")
    private Boolean privateProp;

    @JsonProperty("contributors_url")
    private String contributors_url;

    @JsonProperty("has_downloads")
    private Boolean has_downloads;

    @JsonProperty("notifications_url")
    private String notifications_url;

    @JsonProperty("open_issues_count")
    private Integer open_issues_count;

    @JsonProperty("description")
    private String description;

    @JsonProperty("created_at")
    private String created_at;

    @JsonProperty("watchers")
    private Integer watchers;

    @JsonProperty("deployments_url")
    private String deployments_url;

    @JsonProperty("keys_url")
    private String keys_url;

    @JsonProperty("has_projects")
    private Boolean has_projects;

    @JsonProperty("archived")
    private Boolean archived;

    @JsonProperty("has_wiki")
    private Boolean has_wiki;

    @JsonProperty("updated_at")
    private String updated_at;

    @JsonProperty("comments_url")
    private String comments_url;

    @JsonProperty("stargazers_url")
    private String stargazers_url;

    @JsonProperty("disabled")
    private Boolean disabled;

    @JsonProperty("delete_branch_on_merge")
    private Boolean delete_branch_on_merge;

    @JsonProperty("git_url")
    private String git_url;

    @JsonProperty("has_pages")
    private Boolean has_pages;

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("allow_squash_merge")
    private Boolean allow_squash_merge;

    @JsonProperty("commits_url")
    private String commits_url;

    @JsonProperty("compare_url")
    private String compare_url;

    @JsonProperty("git_commits_url")
    private String git_commits_url;

    @JsonProperty("<")
    private List<String> topics;

    @JsonProperty("blobs_url")
    private String blobs_url;

    @JsonProperty("git_tags_url")
    private String git_tags_url;

    @JsonProperty("merges_url")
    private String merges_url;

    @JsonProperty("downloads_url")
    private String downloads_url;

    @JsonProperty("has_issues")
    private Boolean has_issues;

    @JsonProperty("url")
    private String url;

    @JsonProperty("contents_url")
    private String contents_url;

    @JsonProperty("mirror_url")
    private String mirror_url;

    @JsonProperty("milestones_url")
    private String milestones_url;

    @JsonProperty("teams_url")
    private String teams_url;

    @JsonProperty("fork")
    private Boolean fork;

    @JsonProperty("issues_url")
    private String issues_url;

    @JsonProperty("events_url")
    private String events_url;

    @JsonProperty("issue_events_url")
    private String issue_events_url;

    @JsonProperty("assignees_url")
    private String assignees_url;

    @JsonProperty("open_issues")
    private Integer open_issues;

    @JsonProperty("watchers_count")
    private Integer watchers_count;

    @JsonProperty("node_id")
    private String node_id;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("forks_count")
    private Integer forks_count;
}
