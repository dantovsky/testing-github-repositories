package api.mappings.generic;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {

    //TODO: update this with the error Response from your API
    private String message;
    private String documentation_url;
}
