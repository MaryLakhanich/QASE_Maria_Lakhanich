package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectResponse {
    @Builder.Default boolean status = true;
    ProjectResult result;
}
