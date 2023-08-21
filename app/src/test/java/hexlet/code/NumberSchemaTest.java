package hexlet.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;


public class NumberSchemaTest {
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

}
