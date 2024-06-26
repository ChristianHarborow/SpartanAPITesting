package testFramework.utils;

import testFramework.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class SpartanUtils {
    private static final String SPARTANS = "api/spartans";

    public static Response getAllSpartans(Map<String, String> headers) {
        return RestAssured
                .given()
                .headers(headers)
                .baseUri(TestConfig.getBaseUri())
                .basePath(SPARTANS)
                .get();
    }
}
