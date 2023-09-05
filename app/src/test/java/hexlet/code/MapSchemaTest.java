package hexlet.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MapSchemaTest {
    @Test
    @DisplayName("Required test")
    public void requiredTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, String> testMap = new HashMap<>();

        assertTrue(schema.isValid(testMap));

        schema.required();

        assertFalse(schema.isValid(testMap));
    }
    @Test
    @DisplayName("Sizeof test")
    public void sizeOf() {
        Validator v = new Validator();
        MapSchema schema =  v.map().required().sizeof(3);

        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "1");
        testMap.put("2", "2");
        testMap.put("3", "3");

        assertTrue(schema.isValid(testMap));
    }
}
