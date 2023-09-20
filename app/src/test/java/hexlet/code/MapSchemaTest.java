package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;


public class MapSchemaTest {
    @Test
    public void testMapValidator() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        assertThat(schema.isValid(actual1)).isFalse();
        actual1.put("key2", "value2");
        assertThat(schema.isValid(actual1)).isTrue();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().contains("ya"));
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> actual2 = new HashMap<>();
        actual2.put("name", "Kolya");
        actual2.put("age", 100);
        assertThat(schema.isValid(actual2)).isTrue();

        Map<String, Object> actual3 = new HashMap<>();
        actual3.put("name", "Maya");
        actual3.put("age", null);
        assertThat(schema.isValid(actual3)).isTrue();

        Map<String, Object> actual4 = new HashMap<>();
        actual4.put("name", "");
        actual4.put("age", null);
        assertThat(schema.isValid(actual4)).isFalse();

        Map<String, Object> actual5 = new HashMap<>();
        actual5.put("name", "Valya");
        actual5.put("age", -5);
        assertThat(schema.isValid(actual5)).isFalse();

        Map<String, Object> actual6 = new HashMap<>();
        actual6.put("name", "Ada");
        actual6.put("age", 15);
        assertThat(schema.isValid(actual6)).isFalse();
    }
    @Test
    @DisplayName("Bad data test")
    public void badDataTest() {
        Validator v = new Validator();
        MapSchema schema = v.map().required();

        assertAll(() -> assertFalse(schema.isValid(10)),
                () -> assertFalse(schema.isValid("Test")),
                () -> assertFalse(schema.isValid(null)));
    }
    @Test
    @DisplayName("Required test")
    public void requiredTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();


        assertTrue(schema.isValid(Map.of()));

        schema.required();

        assertFalse(schema.isValid(null));
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
