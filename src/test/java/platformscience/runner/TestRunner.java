package platformscience.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"src/test/java/platformscience/config",
                "src/test/java/platformscience/stepDefinitions"},
        plugin = {"pretty",
                "html:build/test-results/html-report.html",
                "json:build/test-results/json-report.json"
        })
public class TestRunner {
}
