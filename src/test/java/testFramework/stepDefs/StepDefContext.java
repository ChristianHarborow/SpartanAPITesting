package testFramework.stepDefs;

import io.restassured.response.Response;

import java.util.Map;

public class StepDefContext {
    public Map<String, String> headers;
    public Response response;
}
