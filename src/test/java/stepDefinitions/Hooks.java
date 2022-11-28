package stepDefinitions;

import io.cucumber.java.Scenario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @BeforeAll
    public void logger(Scenario scenario){
        LOGGER.info("[STARTED] Scenario: " + scenario.getName());
    }

    @AfterAll
    public void tearDown(Scenario scenario) {
           LOGGER.info("[ENDED] Scenario: " + scenario.getName());
    }

}
