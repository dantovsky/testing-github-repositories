package api.mappings.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * This class is used for POST and PATCH.
 * @author Dante Ferreira Marinho
 */
public class RepoBody {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("auto_init")
    private Boolean auto_init;

    @JsonProperty("homepage")
    private String homepage;

    @JsonProperty("private")
    private Boolean privateProperty;

    @JsonProperty("visibility")
    private String visibility;

    @JsonProperty("security_and_analysis")
    private Object security_and_analysis;

    @JsonProperty("has_issues")
    private Boolean has_issues;

    @JsonProperty("has_projects")
    private Boolean has_projects;

    @JsonProperty("has_wiki")
    private Boolean has_wiki;

    @JsonProperty("is_template")
    private Boolean is_template;

    @JsonProperty("default_branch")
    private String default_branch;

    @JsonProperty("allow_squash_merge")
    private Boolean allow_squash_merge;

    @JsonProperty("allow_merge_commit")
    private Boolean allow_merge_commit;

    @JsonProperty("allow_rebase_merge")
    private Boolean allow_rebase_merge;

    @JsonProperty("delete_branch_on_merge")
    private Boolean delete_branch_on_merge;

    @JsonProperty("archived")
    private Boolean archived;
}
