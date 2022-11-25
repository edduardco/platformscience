package platformscience.utils;

import io.cucumber.spring.ScenarioScope;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@ScenarioScope
public class CleaningResponse {

    private int[] coords;
    private int patches;

}
