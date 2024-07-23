package testFramework.stepDefs;

import org.json.simple.JSONObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import testFramework.schemas.Spartan;
import testFramework.schemas.SpartanDTO;
import testFramework.utils.SpartanUtils;
import testFramework.utils.TestUtils;

import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanStepDefs {
    private static final String TEST_DATA_PATH = "src/test/resources/bodies/spartan/";
    private String body;
    StepDefContext context;

    public SpartanStepDefs(StepDefContext context) {
        this.context = context;
    }

    @When("the get spartans request is made")
    public void theGetSpartansRequestIsMade() {
        context.response = SpartanUtils.getAllSpartans(context.headers);
    }

    @And("a list of spartan objects is returned")
    public void aListOfSpartanObjectsIsReturned() {
        SpartanDTO[] spartans = context.response.as(SpartanDTO[].class);
        assertThat(spartans.length, greaterThan(0));
    }

    @When("the post spartan request is made using {string}")
    public void thePostSpartanRequestIsMadeUsing(String fileName) {
        String path = TEST_DATA_PATH + fileName;
        body = TestUtils.getJsonFromFile(path);
        context.response = SpartanUtils.createSpartan(context.headers, body);
    }

    @And("a SpartanDTO object is returned matching the request body")
    public void aSpartanDTOObjectIsReturnedMatchingTheRequestBody() {
        var requestSpartan = TestUtils.getSchemaFromJson(body, Spartan.class);
        var responseSpartan = context.response.as(SpartanDTO.class);
        assertThat(requestSpartan.getId(), is(responseSpartan.getId()));
        assertThat(requestSpartan.getFirstName(), is(responseSpartan.getFirstName()));
        assertThat(requestSpartan.getLastName(), is(responseSpartan.getLastName()));
        assertThat(requestSpartan.getUniversity(), is(responseSpartan.getUniversity()));
        assertThat(requestSpartan.getDegree(), is(responseSpartan.getDegree()));
        assertThat(requestSpartan.getCourse().getName(), is(responseSpartan.getCourse()));
        assertThat(requestSpartan.getCourse().getStream().getName(), is(responseSpartan.getStream()));
        assertThat(requestSpartan.isGraduated(), is(responseSpartan.isGraduated()));
    }

    @And("a message is returned describing the missing fields")
    public void aMessageIsReturnedDescribingTheMissingFields() {
        var errors = new JSONObject(context.response.jsonPath().getJsonObject("errors"));
        var courseErrors = errors.get("Course");
        var lastnameErrors = errors.get("LastName");
        var firstnameErrors = errors.get("FirstName");

        assertThat(courseErrors, is(List.of("The Course field is required.")));
        assertThat(lastnameErrors, is(List.of("The LastName field is required.")));
        assertThat(firstnameErrors, is(List.of("The FirstName field is required.")));
    }

    @And("a message is returned describing the missing body")
    public void aMessageIsReturnedDescribingTheMissingBody() {
        var errors = new JSONObject(context.response.jsonPath().getJsonObject("errors"));
        var emptyErrors = errors.get("");
        var spartanErrors = errors.get("spartan");

        assertThat(emptyErrors, is(List.of("A non-empty request body is required.")));
        assertThat(spartanErrors, is(List.of("The spartan field is required.")));
    }

    @When("the get spartan request is made to id {string}")
    public void theGetSpartanRequestIsMadeToId(String id) {
        context.response = SpartanUtils.getSpartan(context.headers, id);
    }

    @And("a SpartanDTO object is returned matching {string}")
    public void aSpartanDTOObjectIsReturnedMatchingTheExistingSpartan(String fileName) {
        String path = TEST_DATA_PATH + fileName;
        String body = TestUtils.getJsonFromFile(path);
        var existingSpartan = TestUtils.getSchemaFromJson(body, SpartanDTO.class);
        var responseSpartan = context.response.as(SpartanDTO.class);
        assertThat(responseSpartan, equalTo(existingSpartan));
    }

    @When("the put spartan request is made to spartan id {string} using {string}")
    public void thePutSpartanRequestIsMadeToSpartanIdUsing(String id, String fileName) {
        String path = TEST_DATA_PATH + fileName;
        body = TestUtils.getJsonFromFile(path);
        context.response = SpartanUtils.updateSpartan(context.headers, id, body);
    }

    @And("spartan with id {string} remains unchanged and matches {string}")
    public void spartanWithIdRemainsUnchangedAndMatches(String id, String fileName) {
        String path = TEST_DATA_PATH + fileName;
        String body = TestUtils.getJsonFromFile(path);
        var originalSpartan = TestUtils.getSchemaFromJson(body, SpartanDTO.class);
        context.response = SpartanUtils.getSpartan(context.headers, id);
        var currentSpartan = context.response.as(SpartanDTO.class);
        assertThat(currentSpartan, equalTo(originalSpartan));
    }
}
