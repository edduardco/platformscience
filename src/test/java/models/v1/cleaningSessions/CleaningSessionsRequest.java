package models.v1.cleaningSessions;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ScenarioScope
public class CleaningSessionsRequest {

    private int[] roomSize;
    private int[] coords;
    private ArrayList<int[]> patches;
    private String instructions;

}
