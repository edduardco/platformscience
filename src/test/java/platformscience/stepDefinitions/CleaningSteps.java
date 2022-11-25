package platformscience.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import platformscience.utils.CleaningResponse;
import platformscience.utils.PlatformscienceEnum;
import platformscience.utils.CleaningRequest;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CleaningSteps {

    private RequestSpecification requestSpec;
    private ResponseSpecification responseSpec;
    private Response response;

    @Autowired
    private Hooks hook;

    @Autowired
    protected CleaningRequest cleaningRequest;

    @Autowired
    protected CleaningResponse cleaningResponse;

    @Given("A room size of {int} by {int}")
    public void setRoomSize(int x, int y) {
        cleaningRequest.setRoomSize(new int[]{x, y});
    }

    @Given("robot initial coordinates of {int} and {int}")
    public void setInitialCoordinates(int x, int y) {
        cleaningRequest.setCoords(new int[]{x, y});
    }

    @Given("dirt patches at")
    public void setDirtPatches(DataTable table) {
        Queue<int[]> patches = new LinkedList<>();
        List<Map<String, Integer>> rows = table.asMaps(String.class, Integer.class);
        for (Map<String, Integer> columns : rows) {
            patches.add(new int[]{columns.get("X"), columns.get("Y")});
        }
        cleaningRequest.setPatches(patches);
    }

    @Given("movement instructions of {string}")
    public void setInstructions(String instructions) throws IOException {
        cleaningRequest.setInstructions(instructions);
        requestSpec = given().spec(hook.requestSpecification())
                .body(cleaningRequest);
    }

    @When("robot calls service {string} with {string} http request")
    public void callHttpRequest(String resource, String method) {
        PlatformscienceEnum resourceAPI = PlatformscienceEnum.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        switch (method.toUpperCase()) {
            case "POST" -> response = requestSpec.when().post(resourceAPI.getResource());
            default -> System.out.println("Method: " + method + " it's not valid");
        }
    }

    @Then("cleaning calls responds with status code {int}")
    public void verifyApiCallStatusCode(int statusCode) {
        assertEquals(response.getStatusCode(),200);
        JsonPath jsonPath = new JsonPath(response.asString());
        cleaningResponse.setCoords(jsonPath.get("coords"));
        cleaningResponse.setPatches(jsonPath.get("patches"));
    }

    @Then("verify robot final coordinates are {int} and {int}")
    public void verifyFinalCoordinates(int x, int y) {
        assertEquals(new int[]{x, y}, cleaningResponse.getCoords());
    }

    @Then("verify cleaned patches is/are {int}")
    public void verifyCleanedPatches(int patches) {
        assertEquals(patches, cleaningResponse.getPatches());
    }

}
