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
public class License {

    @JsonProperty("html_url")
    private String html_url;

    @JsonProperty("name")
    private String name;

    @JsonProperty("spdx_id")
    private String spdx_id;

    @JsonProperty("key")
    private String key;

    @JsonProperty("url")
    private String url;

    @JsonProperty("node_id")
    private String node_id;
}
