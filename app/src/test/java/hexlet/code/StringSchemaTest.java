package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;


class StringSchemaTest {

    @Test
    @DisplayName("StringSchema test")
    public void requiredTest() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertAll(() -> assertFalse(schema.isValid("")),
                () -> assertFalse(schema.isValid(null)),
                () -> assertFalse(schema.isValid(10)),
                () -> assertTrue(schema.isValid("hexlet")));
    }
    @Test
    @DisplayName("MinLength test")
    public void minLengthTest() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(4);

        assertAll(() -> assertFalse(schema.isValid("")),
                () -> assertFalse(schema.isValid(null)),
                () -> assertTrue(schema.isValid("hexlet")));
    }
    @Test
    @DisplayName("Contains test")
    public void containsTest() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().contains("hexl");

        assertAll(() -> assertFalse(schema.isValid("")),
                () -> assertFalse(schema.isValid(null)),
                () -> assertFalse(schema.isValid("hex")),
                () -> assertTrue(schema.isValid("hexlet")));
    }
}
