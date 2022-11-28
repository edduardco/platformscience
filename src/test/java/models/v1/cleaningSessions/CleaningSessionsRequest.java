package models.v1.cleaningSessions;

import io.cucumber.spring.ScenarioScope;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Builder
@Component
@Getter
@ScenarioScope
@ToString
public class CleaningSessionsRequest {

    private ArrayList<Integer> roomSize;
    private ArrayList<Integer> coords;
    private ArrayList<ArrayList<Integer>> patches;
    private String instructions;

}
