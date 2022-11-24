package platformscience.utils;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

@Component
@Data
@ScenarioScope
public class PlatformsciencePOJO {

    private int[] roomSize;
    private int[] coords;
    private Queue<int[]> patches;
    private String instructions;
}
