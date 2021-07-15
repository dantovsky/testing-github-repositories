package api.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Common {
    public static final String TOKEN = "<******** Personal Access Token (format: 'token ghp_xxxXXxxx') ********>";
    public static final String ACCEPT_HEADER = "application/vnd.github.v3+json;application/vnd.github.nebula-preview+json";
    public static final String REPO_USERNAME = "dantinTests"; // The GitHub repository username
}
