package platformscience.utils;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component
@Data
@ScenarioScope
public class CleaningRequest {

    private int[] roomSize;
    private int[] coords;
    private Queue<int[]> patches;
    private String instructions;

}
