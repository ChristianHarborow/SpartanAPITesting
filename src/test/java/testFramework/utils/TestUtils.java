package testFramework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {
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
