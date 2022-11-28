package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import utils.ApplicationProperties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class BaseStep {

    @Autowired
    protected ApplicationProperties properties;

    protected RequestSpecification requestSpecification;
    protected ResponseSpecification responseSpecification;
    protected Response response;

    public static RequestSpecification requestSpec;

    public RequestSpecification requestSpecification() throws IOException {
        if (requestSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            requestSpec = new RequestSpecBuilder().setBaseUri(properties.getBaseUri())
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
            return requestSpec;
        }
        return requestSpec;
    }

}
