package config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@CucumberContextConfiguration
@DirtiesContext
@SpringBootTest(classes = {SpringContextConfiguration.class})
public class CucumberSpringConfiguration {
}
