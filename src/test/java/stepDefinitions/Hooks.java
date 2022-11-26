package stepDefinitions;

import io.cucumber.spring.ScenarioScope;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import utils.ApplicationProperties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

@Component
@ScenarioScope
public class Hooks {

    @Autowired
    @Lazy
    private ApplicationProperties properties;

    public static RequestSpecification requestSpec;

    public RequestSpecification requestSpecification() throws IOException {
        if (requestSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(properties.getBaseUrl())
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpec;
        }
        return requestSpec;
    }

}
