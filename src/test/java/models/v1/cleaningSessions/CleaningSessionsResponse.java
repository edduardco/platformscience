package models.v1.cleaningSessions;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@ScenarioScope
public class CleaningSessionsResponse {

    private int[] coords;
    private int patches;

}
