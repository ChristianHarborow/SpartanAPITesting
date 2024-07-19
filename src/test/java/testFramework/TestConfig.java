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
    private static final Properties properties = new Properties();
    private static LocalDateTime tokenRetrieved;

    // Read from the config.properties file and store in the properties field
    static {
        try (InputStream inputStream = TestConfig.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                tokenRetrieved = LocalDateTime.parse(properties.getProperty("token_retrieved"));
            } else {
                throw new IOException("Unable to find config.properties");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getBaseUri() {
        return properties.getProperty("base_uri");
    }

    public static String getToken() {
        synchronized (TestConfig.class) {
            if (!isTokenAlive()) {
                properties.setProperty("token", getNewAuthToken());
                tokenRetrieved = LocalDateTime.now();
                properties.setProperty("token_retrieved", tokenRetrieved.toString());
                storeProperties();
            }
            return properties.getProperty("token");
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
            String path = TestConfig.class.getClassLoader().getResource("config.properties").getPath();
            properties.store(new FileWriter(path), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
