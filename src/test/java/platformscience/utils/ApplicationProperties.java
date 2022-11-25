package platformscience.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Data
@EnableConfigurationProperties
public class ApplicationProperties {

    @Value("${BaseUrl}")
    private String baseUrl;
}
