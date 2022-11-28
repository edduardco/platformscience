package stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import models.v1.cleaningSessions.CleaningSessionsResponse;
import org.springframework.context.annotation.Lazy;
import utils.PlatformscienceEnum;
import models.v1.cleaningSessions.CleaningSessionsRequest;

import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class CleaningSteps extends BaseStep {

    public enum CleaningAttribute {
        ROOM_SIZE, COORDS, PATCHES, INSTRUCTIONS
    }

    EnumMap<CleaningAttribute, Object> cleaningEnumMap = new EnumMap<>(CleaningAttribute.class);

    @Autowired
    @Lazy
    public CleaningSessionsRequest cleaningSessionsRequest;

    @Autowired
    @Lazy
    public CleaningSessionsResponse cleaningSessionsResponse;

    @Given("A room size of {int} by {int}")
    public void setRoomSize(int x, int y) {
        cleaningEnumMap.put(CleaningAttribute.ROOM_SIZE, new ArrayList<>(Arrays.asList(x, y)));
    }

    @Given("robot initial coordinates of {int} and {int}")
    public void setInitialCoordinates(int x, int y) {
        cleaningEnumMap.put(CleaningAttribute.COORDS, new ArrayList<>(Arrays.asList(x, y)));
    }

    @Given("dirt patches at")
    public void setDirtPatches(DataTable table) {
        ArrayList<ArrayList<Integer>> patches = new ArrayList<>();
        ArrayList<Integer> patch;
        int x, y;
        List<Map<String, String>> rows = table.asMaps();
        for (Map<String, String> columns : rows) {
            x = Integer.parseInt(columns.get("X"));
            y = Integer.parseInt(columns.get("Y"));
            patch = new ArrayList<>();
            patch.add(x);
            patch.add(y);
            patches.add(patch);
        }
        cleaningEnumMap.put(CleaningAttribute.PATCHES, patches);
    }

    @Given("movement instructions of {string}")
    public void setInstructions(String instructions) {
        cleaningEnumMap.put(CleaningAttribute.INSTRUCTIONS, instructions);
    }

    @When("robot calls cleaning service")
    public void callHttpRequest() throws JsonProcessingException {
        cleaningSessionsRequest = CleaningSessionsRequest.builder()
                .roomSize((ArrayList<Integer>) cleaningEnumMap.get(CleaningAttribute.ROOM_SIZE))
                .coords((ArrayList<Integer>) cleaningEnumMap.get(CleaningAttribute.COORDS))
                .patches((ArrayList<ArrayList<Integer>>) cleaningEnumMap.get(CleaningAttribute.PATCHES))
                .instructions((String) cleaningEnumMap.get(CleaningAttribute.INSTRUCTIONS))
                .build();

        ObjectMapper mapper = new ObjectMapper();
        requestSpecification = given()
                .baseUri(properties.getBaseUri())
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(cleaningSessionsRequest));
        System.out.println("requestSpecification: " + this.requestSpecification.get().asString());

        PlatformscienceEnum resourceAPI = PlatformscienceEnum.valueOf("cleaning");
        System.out.println(resourceAPI.getBasePath());

        responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        System.out.println("CALL: " + requestSpecification.when().post(resourceAPI.getBasePath()));
        response = requestSpecification.when().post(resourceAPI.getBasePath());
    }

    @Then("cleaning calls responds with status code {int}")
    public void verifyApiCallStatusCode(int statusCode) {
        System.out.println("Response:\n" + response);
        assertEquals(response.getStatusCode(),200);
        JsonPath jsonPath = new JsonPath(response.asString());
        cleaningSessionsResponse = CleaningSessionsResponse.builder()
                .coords(jsonPath.get("coords"))
                .patches(jsonPath.get("patches"))
                .build();
    }

    @Then("verify robot final coordinates are {int} and {int}")
    public void verifyFinalCoordinates(int x, int y) {
        assertEquals(Arrays.asList(x, y), cleaningSessionsResponse.getCoords());
    }

    @Then("verify cleaned patches is/are {int}")
    public void verifyCleanedPatches(int patches) {
        assertEquals(patches, cleaningSessionsResponse.getPatches());
    }

}
