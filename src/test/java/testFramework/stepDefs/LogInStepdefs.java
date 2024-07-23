package testFramework.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import testFramework.TestConfig;
import testFramework.schemas.LoginRequest;
import testFramework.utils.AuthUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class LogInStepdefs {
    StepDefContext context;
    private LoginRequest loginBody;

    public LogInStepdefs(StepDefContext context) {
        this.context = context;
    }

    @Given("the credentials used are valid")
    public void theCredentialsUsedAreValid() {
        loginBody = new LoginRequest(TestConfig.getUsername(), TestConfig.getPassword());
    }

    @When("the log in request is made")
    public void theLogInRequestIsMade() {
        context.response = AuthUtils.sendLoginRequest(loginBody);
    }

    @And("an auth token is returned")
    public void anAuthTokenIsReturned() {
        String token = context.response.jsonPath().getJsonObject("token");
        assertThat(token, hasLength(249));
    }

    @Given("the credentials used are {string} and {string}")
    public void theCredentialsUsedAreAnd(String username, String password) {
        username = username.equals("ACTUAL") ? TestConfig.getUsername() : username;
        password = password.equals("ACTUAL") ? TestConfig.getPassword() : password;
        loginBody = new LoginRequest(username, password);
    }
}
