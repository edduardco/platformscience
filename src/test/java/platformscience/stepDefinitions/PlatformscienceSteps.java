package platformscience.stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import platformscience.utils.DirtPatches;
import platformscience.utils.PlatformsciencePOJO;

import java.util.*;

public class PlatformscienceSteps {

    private RequestSpecification resquestSpec;
    private ResponseSpecification responseSpec;
    private Response response;

    @Autowired
    protected PlatformsciencePOJO platformsciencePOJO;

    @Given("A room size of {int} by {int}")
    public void setRoomSize(int x, int y) {
        platformsciencePOJO.setRoomSize(new int[]{x, y});
    }

    @Given("robot initial coordinates of {int} and {int}")
    public void setInitialCoordinates(int x, int y) {
        platformsciencePOJO.setCoords(new int[]{x, y});
    }

    @Given("dirt patches at")
    public void setDirtPatches(DataTable table) {
        Queue<int[]> patches = new LinkedList<>();
        List<Map<String, Integer>> rows = table.asMaps(String.class, Integer.class);
        for (Map<String, Integer> columns : rows) {
            patches.add(new int[]{columns.get("X"), columns.get("Y")});
        }
        platformsciencePOJO.setPatches(patches);
    }

    @Given("movement instructions of {string}")
    public void setRoomSize(String instructions) {
        platformsciencePOJO.setInstructions(instructions);
    }

}
