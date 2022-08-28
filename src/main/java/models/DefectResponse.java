package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefectResponse {
    @Builder.Default boolean status = true;
    DefectResult result;

}
