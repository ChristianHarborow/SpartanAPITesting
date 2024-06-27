package testFramework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
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

    public static Response getAllSpartans(Map<String, String> headers) {
        return RestAssured
                .given()
                .headers(headers)
                .baseUri(TestConfig.getBaseUri())
                .basePath(SPARTANS)
                .get();
    }

    public static Response createSpartan(Map<String, String> headers, String body) {
        return RestAssured
                .given()
                .headers(headers)
                .baseUri(TestConfig.getBaseUri())
                .basePath(SPARTANS)
                .body(body)
                .contentType(ContentType.JSON)
                .post();
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

    public static Spartan getSpartanFromJson(String json) {
        var mapper = new ObjectMapper();
        Spartan spartan;
        try {
            spartan = mapper.readValue(json, Spartan.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return spartan;
    }
}
