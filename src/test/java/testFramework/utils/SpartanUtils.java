package testFramework.utils;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import testFramework.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class SpartanUtils {
    private static final String SPARTANS = "api/spartans";
    private static final String SPECIFIC_SPARTAN = "api/spartans/{id}";

    private static RequestSpecification getCommonSpec(Map<String, String> headers) {
        return RestAssured
                .given()
                .headers(headers)
                .baseUri(TestConfig.getBaseUri());
    }

    public static Response getAllSpartans(Map<String, String> headers) {
        return getCommonSpec(headers)
                .basePath(SPARTANS)
                .get();
    }

    public static Response getSpartan(Map<String, String> headers, String id) {
        return getCommonSpec(headers)
                .basePath(SPECIFIC_SPARTAN)
                .pathParams(Map.of("id", id))
                .get();
    }

    public static Response createSpartan(Map<String, String> headers, String body) {
        return getCommonSpec(headers)
                .basePath(SPARTANS)
                .body(body)
                .contentType(ContentType.JSON)
                .post();
    }

    public static Response updateSpartan(Map<String, String> headers, String id, String body) {
        return getCommonSpec(headers)
                .basePath(SPECIFIC_SPARTAN)
                .pathParams(Map.of("id", id))
                .body(body)
                .contentType(ContentType.JSON)
                .put();
    }
}
