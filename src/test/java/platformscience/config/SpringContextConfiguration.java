package platformscience.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"platformscience.config",
        "platformscience.runner",
        "platformscience.stepDefinitions",
        "platformscience.utils"})
@Configuration
public class SpringContextConfiguration {
}
