package testFramework.stepDefs;

import org.json.simple.JSONObject;
import testFramework.TestConfig;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import testFramework.schemas.SpartanDTO;
import testFramework.utils.SpartanUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanStepDefs {
    private Map<String, String> headers;
    private Response response;
    private String body;

    @BeforeAll
    public static void beforeAll() {
        TestConfig.retrieveAuthTokenIfDead();
    }

    @Before
    public void before() {
        headers = new HashMap<>();
    }

    @Given("the request is authorised")
    public void theRequestIsAuthorised() {
        headers.put("Authorization", "Bearer " + TestConfig.getToken());
    }

    @When("the get spartans request is made")
    public void theGetSpartansRequestIsMade() {
        response = SpartanUtils.getAllSpartans(headers);
    }

    @Then("a {int} status code is given")
    public void aStatusCodeIsGiven(int statusCode) {
        assertThat(response.statusCode(), is(statusCode));
    }

    @And("a list of spartan objects is returned")
    public void aListOfSpartanObjectsIsReturned() {
        SpartanDTO[] spartans = response.as(SpartanDTO[].class);
        assertThat(spartans.length, greaterThan(0));
    }

    @Given("the request is unauthorised")
    public void theRequestIsUnauthorised() {
        // bearer token has not been added to headers
    }

    @When("the post spartan request is made using {string}")
    public void thePostSpartanRequestIsMadeUsing(String bodyFileName) {
        String path = "src/test/resources/bodies/requests/" + bodyFileName;
        body = SpartanUtils.getJsonFromFile(path);
        response = SpartanUtils.createSpartan(headers, body);
    }

    @And("a SpartanDTO object is returned matching the request body")
    public void aSpartanDTOObjectIsReturnedMatchingTheRequestBody() {
        var requestSpartan = SpartanUtils.getSpartanFromJson(body);
        var responseSpartan = response.as(SpartanDTO.class);
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
        var errors = new JSONObject(response.jsonPath().getJsonObject("errors"));
        var courseErrors = errors.get("Course");
        var lastnameErrors = errors.get("LastName");
        var firstnameErrors = errors.get("FirstName");

        assertThat(courseErrors, is(List.of("The Course field is required.")));
        assertThat(lastnameErrors, is(List.of("The LastName field is required.")));
        assertThat(firstnameErrors, is(List.of("The FirstName field is required.")));
    }

    @And("a message is returned describing the missing body")
    public void aMessageIsReturnedDescribingTheMissingBody() {
        var errors = new JSONObject(response.jsonPath().getJsonObject("errors"));
        var emptyErrors = errors.get("");
        var spartanErrors = errors.get("spartan");

        assertThat(emptyErrors, is(List.of("A non-empty request body is required.")));
        assertThat(spartanErrors, is(List.of("The spartan field is required.")));
    }
}
