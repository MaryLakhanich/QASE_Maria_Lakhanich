package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Defect {
    String title;
    String actualResult;
    int severity;
}
