package testFramework.utils;

import testFramework.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testFramework.schemas.LoginRequest;

public class AuthUtils {
    private static final String AUTH_PATH = "Auth/login";

    public static Response sendLoginRequest(LoginRequest body) {
        return RestAssured
                .given()
                    .baseUri(TestConfig.getBaseUri())
                    .basePath(AUTH_PATH)
                    .body(body)
                    .contentType(ContentType.JSON)
                .post();
    }
}
