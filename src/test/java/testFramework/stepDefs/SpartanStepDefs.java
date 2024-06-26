package testFramework.stepDefs;

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
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartanStepDefs {
    private Map<String, String> headers;
    private Response response;

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
}
