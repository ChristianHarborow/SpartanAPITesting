package testFramework.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testFramework.TestConfig;

import java.util.Map;

public class CourseUtils {
    private static final String COURSES = "api/courses";
    private static final String SPECIFIC_COURSE = "api/courses/{id}";

    private static RequestSpecification getCommonSpec(Map<String, String> headers) {
        return RestAssured
                .given()
                .headers(headers)
                .baseUri(TestConfig.getBaseUri());
    }

    public static Response getAllCourses(Map<String, String> headers) {
        return getCommonSpec(headers)
                .basePath(COURSES)
                .get();
    }

    public static Response getCourse(Map<String, String> headers, String id) {
        return getCommonSpec(headers)
                .basePath(SPECIFIC_COURSE)
                .pathParams(Map.of("id", id))
                .get();
    }
}
