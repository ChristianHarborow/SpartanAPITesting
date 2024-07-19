package testFramework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import testFramework.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import testFramework.schemas.Spartan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public static String getJsonFromFile(String filePath) {
        Path path = Paths.get(filePath);
        String json;
        try {
            json = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public static <T> T getSchemaFromJson(String json, Class<T> type) {
        var mapper = new ObjectMapper();
        T schema;
        try {
            schema = mapper.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return schema;
    }
}
