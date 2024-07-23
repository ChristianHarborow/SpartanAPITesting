package testFramework;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("testFramework/stepDefs")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "src/test/resources/features")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "testFramework/stepDefs")
@IncludeTags({
        "Course",
        "Login",
        "Spartan",
//        "Get",
//        "Post",
//        "Put"
})
public class TestRunner {
}
