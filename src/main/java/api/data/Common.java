package api.data;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Common {
    public static final String TOKEN = "token <your personal access token here>";
    public static final String ACCEPT_HEADER = "application/vnd.github.v3+json";
}
