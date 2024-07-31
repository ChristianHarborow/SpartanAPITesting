package testFramework;

import io.restassured.response.Response;
import testFramework.schemas.LoginRequest;
import testFramework.utils.AuthUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

public class TestConfig {
    private static final Properties PROPERTIES = new Properties();
    private static LocalDateTime tokenRetrieved;
    private static final ClassLoader CLASS_LOADER = TestConfig.class.getClassLoader();
    private static final String FILE_NAME = "config.properties";

    // Read from the config.properties file and store in the properties field
    static {
        try (InputStream inputStream = CLASS_LOADER.getResourceAsStream(FILE_NAME)) {
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
                tokenRetrieved = LocalDateTime.parse(PROPERTIES.getProperty("token_retrieved"));
            } else {
                throw new IOException("Unable to find " + FILE_NAME);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUsername() {
        return PROPERTIES.getProperty("username");
    }

    public static String getPassword() {
        return PROPERTIES.getProperty("password");
    }

    public static String getBaseUri() {
        return PROPERTIES.getProperty("base_uri");
    }

    public static String getToken() {
        synchronized (TestConfig.class) {
            if (!isTokenAlive()) {
                tokenRetrieved = LocalDateTime.now();
                PROPERTIES.setProperty("token", getNewAuthToken());
                PROPERTIES.setProperty("token_retrieved", tokenRetrieved.toString());
                storeProperties();
            }
            return PROPERTIES.getProperty("token");
        }
    }

    private static boolean isTokenAlive() {
        return tokenRetrieved.plusMinutes(25).isAfter(LocalDateTime.now());
    }

    private static String getNewAuthToken() {
        var body = new LoginRequest(getUsername(), getPassword());
        Response response = AuthUtils.sendLoginRequest(body);
        return response.jsonPath().getString("token");
    }

    private static void storeProperties() {
        try {
            String path = CLASS_LOADER.getResource(FILE_NAME).getPath();
            PROPERTIES.store(new FileWriter(path), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
