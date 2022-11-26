package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"config",
        "models",
        "runner",
        "stepDefinitions",
        "utils"})
@Configuration
public class SpringContextConfiguration {
}
