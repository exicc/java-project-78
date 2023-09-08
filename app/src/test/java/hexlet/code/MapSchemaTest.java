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
    @Test
    @DisplayName("Nested validation test")
    public void nestedValidation() {
        Validator v = new Validator();
        MapSchema schema =  v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();

        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Egor");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));
    }
}
