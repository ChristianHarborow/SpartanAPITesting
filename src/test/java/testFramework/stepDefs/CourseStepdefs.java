package testFramework.stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import testFramework.schemas.CourseDTO;
import testFramework.utils.CourseUtils;
import testFramework.utils.TestUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class CourseStepdefs {
    private static final String TEST_DATA_PATH = "src/test/resources/bodies/course/";
    StepDefContext context;

    public CourseStepdefs(StepDefContext context) {
        this.context = context;
    }

    @When("the get courses request is made")
    public void theGetCoursesRequestIsMade() {
        context.response = CourseUtils.getAllCourses(context.headers);
    }

    @And("a list of course objects is returned")
    public void aListOfCourseObjectsIsReturned() {
        CourseDTO[] courses = context.response.as(CourseDTO[].class);
        assertThat(courses.length, greaterThan(0));
    }

    @When("the get course request is made to id {string}")
    public void theGetCourseRequestIsMadeToId(String id) {
        context.response = CourseUtils.getCourse(context.headers, id);
    }

    @And("a CourseDTO object is returned matching {string}")
    public void aCourseDTOObjectIsReturnedMatchingTheSpecifiedCourse(String fileName) {
        String path = TEST_DATA_PATH + fileName;
        String body = TestUtils.getJsonFromFile(path);
        var existingSpartan = TestUtils.getSchemaFromJson(body, CourseDTO.class);
        var responseSpartan = context.response.as(CourseDTO.class);
        assertThat(responseSpartan, equalTo(existingSpartan));
    }
}
