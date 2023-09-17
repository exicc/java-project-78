package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;


public class NumberSchemaTest {
    @Test
    @DisplayName("Bad data test")
    public void badDataTest() {
        Validator v = new Validator();
        NumberSchema schema =  v.number().required();
        assertAll(() -> assertFalse(schema.isValid("Test")),
                () -> assertFalse(schema.isValid(Map.of())),
                () -> assertFalse(schema.isValid(Map.of(1, 2))));
    }
    @Test
    @DisplayName("Required test")
    public void requiredTest() {
        Validator v = new Validator();
        NumberSchema schema =  v.number().required();
        assertAll(() -> assertFalse(schema.isValid(null)),
                () -> assertTrue(schema.isValid(1)),
                () -> assertTrue(schema.isValid(-5)));
    }
    @Test
    @DisplayName("Positive number test")
    public void isPositiveTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number().required().positive();
        assertAll(() -> assertFalse(schema.isValid(null)),
                () -> assertTrue(schema.isValid(1)),
                () -> assertFalse(schema.isValid(-111)),
                () -> assertFalse(schema.isValid(-5)));
    }
    @Test
    @DisplayName("Range test")
    public void rangeTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number().required().positive().range(4, 7);
        assertAll(() -> assertFalse(schema.isValid(null)),
                () -> assertTrue(schema.isValid(4)),
                () -> assertTrue(schema.isValid(5)),
                () -> assertTrue(schema.isValid(7)),
                () -> assertFalse(schema.isValid(-5)),
                () -> assertFalse(schema.isValid(10)),
                () -> assertFalse(schema.isValid(2)));
    }
}
